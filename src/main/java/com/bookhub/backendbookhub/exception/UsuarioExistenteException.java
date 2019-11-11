package com.bookhub.backendbookhub.exception;

import lombok.Getter;

@Getter
public class UsuarioExistenteException extends Exception{

    private String telefone;
    private String login;

    public UsuarioExistenteException(String telefone, String login){
        this.telefone = telefone;
        this.login = login;
    }

    @Override
    public String toString(){
        return "Campo telefone, login ou email ja esta sendo usado.";
    }
}
