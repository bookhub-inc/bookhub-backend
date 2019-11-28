package com.bookhub.backendbookhub;

import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
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


	@Bean
	public MySQLJDBCDataModel mySQLJDBCDataModel(DataSource dataSource){
		return new MySQLJDBCDataModel(dataSource,"recomendador_livro_usuario",
				"id_usuario",
				"id_livro",
				"nota",
				"");
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
}
