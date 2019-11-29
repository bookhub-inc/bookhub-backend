package com.bookhub.backendbookhub.api;

import com.bookhub.backendbookhub.entity.LivroEntity;
import com.bookhub.backendbookhub.recomendador.UsuarioRecomendadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.util.ArrayList;
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
    private LocalDateTime execucaoCorrenteTaxa = null;
    private ArrayList<String> listaTaxas = new ArrayList<>();

    @ResponseStatus(OK)
    @ApiOperation(value = "Inicializa job assíncrono de recomendação de livro para todos os usuarios que tenham pelo menos 1 livro na estante com nota",
            notes = "Inicializa job assíncrono de recomendação de livro para todos os usuarios que tenham pelo menos 1 livro na estante com nota")
    @GetMapping("/sugestao/cache")
    public String geraRecomendacoesTodosUsuarios() {

        if (getTheadPoolRecomendacao().getActiveCount() > 0) {
            return "Já está sendo recomendado livros para usuários, espere acabar!";
        }
        startTimer("Inicia recomendação para todos usuários");
        execucaoCorrente = LocalDateTime.now();
        usuarioRecomendadorService.geraRecomendacoesTodosUsuarios(timer);

        return "Inicou o processo de recomendar livros! ";
    }


    @ResponseStatus(OK)
    @ApiOperation(value = "Verifica se o job de recomendação está sendo executado",
            notes = "Verifica se o job de recomendação está sendo executado")
    @GetMapping("/sugestao/rodando")
    public String verificaExecucao() {

        if (getTheadPoolRecomendacao().getActiveCount() > 0) {
            return "Executando : " + timer.currentTaskName() + "\n Executada " + execucaoCorrente;
        }

        return "Não há nada sendo executado ";
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Relatório das ultimas execuções do job de recomendação",
            notes = "Relatório das ultimas execuções do job de recomendação")
    @GetMapping("/sugestao/relatorio")
    public String relatorio() {

        return timer.prettyPrint();
    }


    @ResponseStatus(OK)
    @ApiOperation(value = "Inicializa job assíncrono de recomendação de livro para um usuario específico",
            notes = "Inicializa job de recomendação de livro para um usuario específico")
    @GetMapping("/sugestao/cache/{idUsuario}")
    public String geraRecomendacaoUsuario(@ApiParam(example = "1", required = true) @PathVariable("idUsuario") final Integer idUsuario) {

        if (getTheadPoolRecomendacao().getActiveCount() > 0) {
            return "Já está sendo recomendado livros para usuários, espere acabar!";
        }
        execucaoCorrente = LocalDateTime.now();
        startTimer("Start recomendação idUsuario: " + idUsuario);

        usuarioRecomendadorService.geraRecomendacoes(idUsuario, timer);

        return "Inicou o processo de recomendar livros! ";
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Inicializa job assíncrono de recomendação de livro para um usuario específico", notes = "Inicializa job de recomendação de livro para um usuario específico")
    @GetMapping("/sugestao/{idUsuario}")
    public ResponseEntity<List<LivroEntity>> buscaLivrosRecomendados(@ApiParam(example = "1", required = true) @PathVariable("idUsuario") final Integer idUsuario) {
        return new ResponseEntity<>(usuarioRecomendadorService.buscaLivrosRecomendados(idUsuario), OK);
    }


    @ResponseStatus(OK)
    @ApiOperation(value = "Inicializa verificação de erro de forma assíncrona", notes = "Inicializa verificação de erro de forma assíncrona")
    @GetMapping("/taxa")
    public String verificaTaxaErro() {

        if (getTheadPoolTaxa().getActiveCount() > 0) {
            return "Executando  desde  " + execucaoCorrenteTaxa;
        }

        execucaoCorrenteTaxa = LocalDateTime.now();

        usuarioRecomendadorService.verificaTaxaErro(listaTaxas);

        return "Executando";
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Busca relatório de resultados da taxa de falha do recomendador", notes = "Busca relatório de resultados da taxa de falha do recomendador")
    @GetMapping("/taxa/relatorio")
    public String relatorioTaxas() {

        if(listaTaxas.size() == 0)
            return "Ainda não foi gerado nenhum relatorio.";

        StringBuilder resultado = new StringBuilder("Relatório: ");

        listaTaxas.forEach(resultado::append);

        return resultado.toString();
    }

    private void startTimer(String task) {
        if (timer.isRunning()) {
            timer.stop();
        }
        timer.start(task);
    }

    public ThreadPoolTaskExecutor getTheadPoolRecomendacao() {
        return (ThreadPoolTaskExecutor) context.getBean("recomendacaoLivros");
    }

    public ThreadPoolTaskExecutor getTheadPoolTaxa() {
        return (ThreadPoolTaskExecutor) context.getBean("taxa");
    }
}
