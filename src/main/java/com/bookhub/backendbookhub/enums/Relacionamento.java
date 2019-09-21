package com.bookhub.backendbookhub.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Arthur Rio
 * @since 9/16/19
 */
public enum Relacionamento {

 SOLTEIRO,CASADO,VIUVO,DIVORCIADO
 ;


  public static List<String> getValues(){
   return Arrays.stream(Relacionamento.values()).map(Relacionamento::name).collect(Collectors.toList());
  }


}
