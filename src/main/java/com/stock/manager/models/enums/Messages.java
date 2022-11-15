package com.stock.manager.models.enums;

public enum Messages {

    ERRO_CRUD("Ocorreu um erro ao executar essa operação"),
    ERRO_CREATE("Ocorreu um erro ao tentar executar o método de criação"),
    ERRO_UPDATE("Ocorreu um erro ao tentar executar o método de edição"),
    ERRO_DELETE("Ocorreu um erro ao tentar executar o método de exclusão"),
    ERRO_READ("Ocorreu um erro ao tentar executar o método de visualização");

    private String message;

    Messages(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
