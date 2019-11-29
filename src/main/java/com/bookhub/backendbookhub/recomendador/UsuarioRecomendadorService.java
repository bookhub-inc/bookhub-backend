package com.bookhub.backendbookhub.recomendador;

import com.bookhub.backendbookhub.dao.UsuarioEstanteDAO;
import com.bookhub.backendbookhub.entity.LivroEntity;
import com.bookhub.backendbookhub.entity.LivroRecomendadoEntity;
import com.bookhub.backendbookhub.service.LivroService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UsuarioRecomendadorService {

    @Autowired
    private UsuarioEstanteDAO usuarioEstanteDAO;

    @Autowired
    private ReloadFromJDBCDataModel mySQLJDBCDataModel;

    @Autowired
    private LivroService livroService;

    @Autowired
    private Recommender recommender;

    @SneakyThrows
    public List<RecommendedItem> buscaRecomendacoes(Integer idUsuario) {

//        Recommender recommender = new RecomendadorBuilder().buildRecommender(mySQLJDBCDataModel);
//
//        Recommender cachingRecommender = new CachingRecommender(recommender);

        return recommender.recommend(idUsuario, 2);

    }

    public void salvaRecomendacoes(LivroRecomendadoEntity livroRecomendadoEntity) {

        usuarioEstanteDAO.insereLivroRecomendado(livroRecomendadoEntity);

    }

    public List<LivroEntity> buscaLivrosRecomendados(Integer idUsuario) {

        List<LivroRecomendadoEntity> listaLivrosRecomendados = usuarioEstanteDAO.buscaLivrosRecomendados(idUsuario);

        List<LivroEntity> listaLivrosRetorno = new ArrayList<>();

        for (LivroRecomendadoEntity livroRecomendado : listaLivrosRecomendados) {

            LivroEntity livro = livroService.find(livroRecomendado.getIdLivro());
            if (Objects.nonNull(livro)) {
                listaLivrosRetorno.add(livro);
            }
        }

        return listaLivrosRetorno;

    }

    @Async("recomendacaoLivros")
    @Transactional
    public void geraRecomendacoesTodosUsuarios(StopWatch timer) {

        List<Integer> listaUsuarios = usuarioEstanteDAO.buscaUsuariosEstante();

        if (Objects.nonNull(listaUsuarios)) {
            listaUsuarios
                    .forEach(this::geraRecomendacoes);
        }

        timer.stop();
    }

    @Async("recomendacaoLivros")
    @Transactional
    public void geraRecomendacoes(Integer idUsuario,StopWatch timer) {

        geraRecomendacoes(idUsuario);
        timer.stop();
    }


    @Async("recomendacaoLivros")
    @Transactional
    public void geraRecomendacoes(Integer idUsuario) {

        List<UsuarioRecomendadorVO> listaUsuarios = usuarioEstanteDAO.buscaUsuariosRecomendar(idUsuario);

        usuarioEstanteDAO.removeRecomendador(idUsuario);
        usuarioEstanteDAO.removeLivrosRecomendados(idUsuario);

        for (UsuarioRecomendadorVO usuario : listaUsuarios) {

            usuarioEstanteDAO.insereRecomendador(UsuarioRecomendadorVO.builder()
                    .idUsuario(usuario.getIdUsuario())
                    .idLivro(usuario.getIdLivro())
                    .nota(usuario.getNota())
                    .build());

        }


        List<RecommendedItem> recommendedItemList = buscaRecomendacoes(idUsuario);

        for (RecommendedItem item : recommendedItemList) {
            usuarioEstanteDAO.insereLivroRecomendado(new LivroRecomendadoEntity(null,
                    idUsuario,
                    Long.valueOf(item.getItemID()).intValue()));

        }

    }


    @Async("taxa")
    @SneakyThrows
    public void verificaTaxaErro(ArrayList<String> listTaxas) {

        LocalDateTime dataInicio = LocalDateTime.now();

        DataModel myModel = mySQLJDBCDataModel;
        RecommenderEvaluator evaluator =
                new AverageAbsoluteDifferenceRecommenderEvaluator();
        double evaluation = evaluator.evaluate(new RecomendadorBuilder(),null, myModel, 0.9, 1.0);

        listTaxas.add(String.format("Taxa: %d - Inicio: %s - Fim: %s",evaluation,dataInicio,LocalDateTime.now()) + "\n");

    }


}
