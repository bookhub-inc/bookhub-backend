package com.bookhub.backendbookhub.api;

import com.bookhub.backendbookhub.recomendador.UsuarioRecomendadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Api(value="Recomendador",tags = "Recomendador")
public class RecomendadorAPI {

    @Autowired
    private UsuarioRecomendadorService usuarioRecomendadorService;

    @Autowired
    private ApplicationContext context;

    @ResponseStatus(OK)
    @GetMapping("/sugestao/cache")
    public String geraRecomendacoesTodosUsuarios() {

        ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) context.getBean("recomendacaoLivros");

        if(executor.getActiveCount() > 0 ) {
            return "Já está sendo recomendado livros para usuários, espere acabar!";
        }

        usuarioRecomendadorService.geraRecomendacoesTodosUsuarios();

        return "Inicou o processo de recomendar livros! ";
    }

    @ResponseStatus(OK)
    @GetMapping("/sugestao/cache/{idUsuario}")
    public String geraRecomendacaoUsuario(@ApiParam(example = "1",required = true) @PathVariable("idUsuario") final Integer idUsuario) {

        ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) context.getBean("recomendacaoLivros");

        if(executor.getActiveCount() > 0 ) {
            return "Já está sendo recomendado livros para usuários, espere acabar!";
        }

        usuarioRecomendadorService.geraRecomendacoes(idUsuario);

        return "Inicou o processo de recomendar livros! ";
    }


    @ResponseStatus(OK)
    @GetMapping("/sugestao")
    public String verificaTaxaErro(){
        return usuarioRecomendadorService.verificaTaxaErro();
    }

}
