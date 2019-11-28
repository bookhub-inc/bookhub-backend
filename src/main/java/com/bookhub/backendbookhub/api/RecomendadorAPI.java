package com.bookhub.backendbookhub.api;

import com.bookhub.backendbookhub.recomendador.UsuarioRecomendadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Api(value="Recomendador",tags = "Recomendador")
public class RecomendadorAPI {

    @Autowired
    private UsuarioRecomendadorService usuarioRecomendadorService;

    @ResponseStatus(OK)
    @GetMapping("/sugestao/cache")
    public String geraRecomendacoesTodosUsuarios() {

        usuarioRecomendadorService.geraRecomendacoesTodosUsuarios();

        return "Inicou o processo de recomendar livros! ";
    }

    @ResponseStatus(OK)
    @GetMapping("/sugestao/cache/{idUsuario}")
    public String geraRecomendacaoUsuario(@ApiParam(example = "1",required = true) @PathVariable("idUsuario") final Integer idUsuario) {

        usuarioRecomendadorService.geraRecomendacoes(idUsuario);

        return "Inicou o processo de recomendar livros! ";
    }


    @ResponseStatus(OK)
    @GetMapping("/sugestao")
    public String verificaTaxaErro(){
        return usuarioRecomendadorService.verificaTaxaErro();
    }

}
