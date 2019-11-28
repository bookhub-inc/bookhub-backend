package com.bookhub.backendbookhub.recomendador;

import com.bookhub.backendbookhub.dao.UsuarioEstanteDAO;
import com.bookhub.backendbookhub.entity.LivroEntity;
import com.bookhub.backendbookhub.entity.LivroRecomendadoEntity;
import com.bookhub.backendbookhub.service.LivroService;
import lombok.SneakyThrows;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.DataModelBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.GenericBooleanPrefDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.common.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioRecomendadorService {

    @Autowired
    private UsuarioEstanteDAO usuarioEstanteDAO;

    @Autowired
    private MySQLJDBCDataModel mySQLJDBCDataModel;

    @Autowired
    private LivroService livroService;

    @SneakyThrows
    public List<RecommendedItem> buscaRecomendacoes(Integer idUsuario) {

        Recommender recommender = new RecomendadorBuilder().buildRecommender(mySQLJDBCDataModel);

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

    @Transactional
    public void geraRecomendacoesTodosUsuarios() {

        List<Integer> listaUsuarios = usuarioEstanteDAO.buscaUsuariosEstante();

        if (Objects.nonNull(listaUsuarios)) {
            listaUsuarios
                    .forEach(this::geraRecomendacoes);
        }
    }


    @Transactional
    public void geraRecomendacoes(Integer idUsuario) {

        List<UsuarioRecomendadorVO> listaUsuarios = usuarioEstanteDAO.buscaUsuariosRecomendar(idUsuario);

        usuarioEstanteDAO.removeRecomendador(idUsuario);

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


    @SneakyThrows
    public String verificaTaxaErro() {
        RandomUtils.useTestSeed();
        //calcula a média absoluta dos testes para informar a taxa de erro
        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
        RecommenderBuilder builder = new RecomendadorBuilder();
        //90% para treino e 10% para teste
        return String.valueOf(evaluator.evaluate(builder, null, mySQLJDBCDataModel, 0.9, 1.0));
    }

    public static DataModelBuilder createNoPrefDataModelBuilder(){
        return new DataModelBuilder() {
            public DataModel buildDataModel(
                    FastByIDMap<PreferenceArray> trainingData) {
                return new GenericBooleanPrefDataModel(
                        GenericBooleanPrefDataModel.toDataMap(trainingData));
            }
        };
    }
}
