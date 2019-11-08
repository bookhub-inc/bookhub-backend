package com.bookhub.backendbookhub.api;

import com.bookhub.backendbookhub.entity.TopicoEntity;
import com.bookhub.backendbookhub.recomendador.UsuarioRecomendadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Api(value="Recomendador",tags = "Recomendador",description = " ")
public class RecomendadorAPI {

    @Autowired
    private UsuarioRecomendadorService usuarioRecomendadorService;

    @ResponseStatus(OK)
    @GetMapping("/sugestao/{idUsuario}")
    public List<RecommendedItem> find(@ApiParam(example = "10",required = true) @PathVariable("idUsuario") final Integer id) {
        return usuarioRecomendadorService.recomendarLivro(id);
    }


    @ResponseStatus(OK)
    @GetMapping("/sugestao")
    public String verificaTaxaErro(){
        return usuarioRecomendadorService.verificaTaxaErro();
    }

}
