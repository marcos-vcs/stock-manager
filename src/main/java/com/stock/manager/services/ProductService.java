package com.stock.manager.services;

import com.stock.manager.exceptions.ErroCrudException;
import com.stock.manager.models.Product;
import com.stock.manager.models.enums.Messages;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class ProductService implements CrudInterface<Product> {

    private final MongoTemplate template;

    public ProductService(MongoTemplate template){
        this.template = template;
    }

    @Override
    public void create(Product object) throws ErroCrudException {
        try{
            object.setCreationDate(Calendar.getInstance().getTime());
            template.save(object);
        }catch (Exception e){
            throw new ErroCrudException(Messages.ERRO_CRUD.getMessage());
        }
    }

    @Override
    public void update(Product object) throws ErroCrudException {
        try{
            Query query = new Query(Criteria.where("id").is(object.getId()));
            Update update = new Update()
                    .set("name", object.getName())
                    .set("description", object.getDescription())
                    .set("quantity", object.getQuantity());
            var quantityModified = this.template.updateFirst(query, update, Product.class).getModifiedCount();

            if(quantityModified == 0){
                throw new ErroCrudException(Messages.ERRO_UPDATE.getMessage());
            }

        }catch (Exception e){
            throw new ErroCrudException(Messages.ERRO_CRUD.getMessage());
        }
    }

    @Override
    public void updateQuantity(String id, int quantity) throws ErroCrudException {
        try{
            Query query = new Query(Criteria.where("id").is(id));
            Update update = new Update()
                    .set("quantity", quantity);
            var quantityModified = this.template.updateFirst(query, update, Product.class).getModifiedCount();

            if(quantityModified == 0){
                throw new ErroCrudException(Messages.ERRO_CRUD.getMessage());
            }
        }catch (Exception e){
            throw new ErroCrudException(Messages.ERRO_CRUD.getMessage());
        }
    }

    @Override
    public void delete(String id) throws ErroCrudException {
        try{
            Query query = new Query(Criteria.where("id").is(id));
            template.remove(query, Product.class);
        }catch (Exception e){
            throw new ErroCrudException(Messages.ERRO_CRUD.getMessage());
        }
    }

    @Override
    public List<Product> read(int skip, int limit) throws ErroCrudException {
        try{
            Query query = new Query();
            query.with(Sort.by(Sort.Direction.DESC, "name"));
            query.skip(skip).limit(limit);
            List<Product> products;
            products = template.find(query, Product.class);
            return products;
        }catch (Exception e){
            throw new ErroCrudException(Messages.ERRO_CRUD.getMessage());
        }
    }

    @Override
    public List<Product> findByName(int skip, int limit, String name) throws ErroCrudException {
        try{
            Query query = new Query(Criteria.where("name").regex(name, "i"));
            query.with(Sort.by(Sort.Direction.DESC, "name"));
            query.skip(skip).limit(limit);
            List<Product> products;
            products = template.find(query, Product.class);
            return products;
        }catch (Exception e){
            throw new ErroCrudException(Messages.ERRO_CRUD.getMessage());
        }
    }
}
