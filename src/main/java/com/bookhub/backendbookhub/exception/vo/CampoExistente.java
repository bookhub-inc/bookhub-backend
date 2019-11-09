package com.bookhub.backendbookhub.exception.vo;

import com.bookhub.backendbookhub.exception.CampoExistenteException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampoExistente {

    private String campo;
    private String valor;

    public CampoExistente(CampoExistenteException e){
        campo = e.getCampoDulicado();
        valor = e.getValor();
    }

    private String getMensagem(){
        return String.format("Campo %s com valor %s já está cadastrado",campo,valor);
    }
}
