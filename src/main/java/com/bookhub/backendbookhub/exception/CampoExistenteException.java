package com.bookhub.backendbookhub.exception;

import lombok.Getter;

import java.sql.SQLIntegrityConstraintViolationException;

@Getter
public class CampoExistenteException  extends SQLIntegrityConstraintViolationException {

    private String campoDulicado;
    private String valor;

    public CampoExistenteException(String campoDulicado, String valor,Exception e){
        super(e);
        this.campoDulicado = campoDulicado;
        this.valor = valor;
    }

}
