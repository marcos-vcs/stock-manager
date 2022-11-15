package com.stock.manager.exceptions;

public class ErroCrudException extends Exception{
    public ErroCrudException(String description){
        super(description);
    }
}
