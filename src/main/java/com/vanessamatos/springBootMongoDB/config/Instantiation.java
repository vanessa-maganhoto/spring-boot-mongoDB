package com.vanessamatos.springBootMongoDB.config;

import com.vanessamatos.springBootMongoDB.domain.User;
import com.vanessamatos.springBootMongoDB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userReposiroty;
    @Override
    public void run(String... arg0) throws Exception {

        userReposiroty.deleteAll();

        User maria = new User(null, "Maria Antonia", "maria@gmail.com");
        User bianca = new User(null, "Bianca Andrade", "bianca@gmail.com");
        User fabricio = new User(null, "Fabricio Mendes", "fabricio@gmail.com");

        userReposiroty.saveAll(Arrays.asList(maria, bianca, fabricio));
    }
}
