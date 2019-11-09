package com.bookhub.backendbookhub.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionUtils {


    public static String buscaCampoDuplicado(String texto) {

        String regex = "for key '(?<meuGrupo>.*?)'";

        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher comparator = pattern.matcher(texto);

        return comparator.group("meuGrupo");

    }

    public static String buscaValorDuplicado(String texto) {

        String regex = "Duplicate entry '(?<meuGrupo>.*?)'";

        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher comparator = pattern.matcher(texto);

        return comparator.group("meuGrupo");

    }

}
