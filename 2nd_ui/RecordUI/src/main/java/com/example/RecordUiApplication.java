package com.example;

import com.db.hibernate.options.DBCompositionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecordUiApplication {
	private static final DBCompositionFactory dbCompositionFactory = new DBCompositionFactory();

	public static void main(String[] args) {
		SpringApplication.run(RecordUiApplication.class, args);
	}

	public static DBCompositionFactory getDbCompositionFactory() {
		return dbCompositionFactory;
	}
}
