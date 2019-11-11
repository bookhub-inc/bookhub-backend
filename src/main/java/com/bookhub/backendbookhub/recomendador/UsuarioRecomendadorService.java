package com.bookhub.backendbookhub.recomendador;

import com.bookhub.backendbookhub.dao.UsuarioEstanteDAO;
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

import java.util.List;

@Service
public class UsuarioRecomendadorService {

    @Autowired
    private UsuarioEstanteDAO usuarioEstanteDAO;

    @Autowired
    private MySQLJDBCDataModel mySQLJDBCDataModel;


    @SneakyThrows
    public List<RecommendedItem> recomendarLivro(Integer idUsuario)  {


        Recommender recommender = new RecomendadorBuilder().buildRecommender(mySQLJDBCDataModel);

        return recommender.recommend(idUsuario, 3);
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

    public static DataModelBuilder createNoPrefDataModelBuilder()
            throws TasteException {
        return new DataModelBuilder() {
            public DataModel buildDataModel(
                    FastByIDMap<PreferenceArray> trainingData) {
                return new GenericBooleanPrefDataModel(
                        GenericBooleanPrefDataModel.toDataMap(trainingData));
            }
        };
    }
}
