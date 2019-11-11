package com.bookhub.backendbookhub;

import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class BackendBookhubApplication {

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
}
