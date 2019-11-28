package com.bookhub.backendbookhub.api;

import com.bookhub.backendbookhub.entity.LivroEntity;
import com.bookhub.backendbookhub.recomendador.UsuarioRecomendadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Api(value = "Recomendador", tags = "Recomendador")
public class RecomendadorAPI {

    @Autowired
    private UsuarioRecomendadorService usuarioRecomendadorService;

    @Autowired
    private ApplicationContext context;

    private StopWatch timer = new StopWatch();

    private LocalDateTime execucaoCorrente = null;

    @ResponseStatus(OK)
    @GetMapping("/sugestao/cache")
    public String geraRecomendacoesTodosUsuarios() {

        if (getTheadPool().getActiveCount() > 0) {
            return "Já está sendo recomendado livros para usuários, espere acabar!";
        }
        startTimer("Inicia recomendação para todos usuários");
        execucaoCorrente = LocalDateTime.now();
        usuarioRecomendadorService.geraRecomendacoesTodosUsuarios(timer);

        return "Inicou o processo de recomendar livros! ";
    }


    @ResponseStatus(OK)
    @GetMapping("/sugestao/rodando")
    public String verificaExecucao() {

        if (getTheadPool().getActiveCount() > 0) {
            return "Executando : " + timer.currentTaskName() + "\n Executada " + execucaoCorrente;
        }

        return "Não há nada sendo executado ";
    }

    @ResponseStatus(OK)
    @GetMapping("/sugestao/relatorio")
    public String relatorio() {

        return timer.prettyPrint();
    }


    @ResponseStatus(OK)
    @GetMapping("/sugestao/cache/{idUsuario}")
    public String geraRecomendacaoUsuario(@ApiParam(example = "1", required = true) @PathVariable("idUsuario") final Integer idUsuario) {

        if (getTheadPool().getActiveCount() > 0) {
            return "Já está sendo recomendado livros para usuários, espere acabar!";
        }
        execucaoCorrente = LocalDateTime.now();
        startTimer("Start recomendação idUsuario: " + idUsuario);

        usuarioRecomendadorService.geraRecomendacoes(idUsuario, timer);

        return "Inicou o processo de recomendar livros! ";
    }

    @ResponseStatus(OK)
    @GetMapping("/sugestao/{idUsuario}")
    public ResponseEntity<List<LivroEntity>> buscaLivrosRecomendados(@ApiParam(example = "1", required = true) @PathVariable("idUsuario") final Integer idUsuario) {
        return new ResponseEntity<>(usuarioRecomendadorService.buscaLivrosRecomendados(idUsuario), OK);
    }


    @ResponseStatus(OK)
    @GetMapping("/sugestao")
    public String verificaTaxaErro() {
        return usuarioRecomendadorService.verificaTaxaErro();
    }

    public ThreadPoolTaskExecutor getTheadPool() {
        return (ThreadPoolTaskExecutor) context.getBean("recomendacaoLivros");
    }


    private void startTimer(String task) {
        if (timer.isRunning()) {
            timer.stop();
        }
        timer.start(task);
    }
}
