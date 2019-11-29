package com.bookhub.backendbookhub;

import com.bookhub.backendbookhub.recomendador.RecomendadorBuilder;
import lombok.SneakyThrows;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class BackendBookhubApplication  {

	public static void main(String[] args) {
		SpringApplication.run(BackendBookhubApplication.class, args);
	}

	@SneakyThrows
	@Bean
	public ReloadFromJDBCDataModel mySQLJDBCDataModel(DataSource dataSource){

		MySQLJDBCDataModel mySQLJDBCDataModel = new MySQLJDBCDataModel(dataSource, "recomendador_livro_usuario",
				"id_usuario",
				"id_livro",
				"nota",
				"");

		return new ReloadFromJDBCDataModel(mySQLJDBCDataModel);

	}

	@SneakyThrows
	@Bean
	public Recommender recommender(ReloadFromJDBCDataModel model){
		Recommender recommender = new RecomendadorBuilder().buildRecommender(model);
		return new CachingRecommender(recommender);
	}

	@Bean(name = "recomendacaoLivros")
	public Executor asyncExecutor() {
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(1);
		executor.setQueueCapacity(1);
		executor.initialize();
		return executor;
	}

	@Bean(name = "taxa")
	public Executor taxa() {
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(1);
		executor.setQueueCapacity(1);
		executor.initialize();
		return executor;
	}

}
