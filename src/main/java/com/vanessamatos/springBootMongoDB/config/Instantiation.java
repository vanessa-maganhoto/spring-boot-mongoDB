package com.vanessamatos.springBootMongoDB.config;

import com.vanessamatos.springBootMongoDB.domain.Post;
import com.vanessamatos.springBootMongoDB.domain.User;
import com.vanessamatos.springBootMongoDB.dto.AuthorDTO;
import com.vanessamatos.springBootMongoDB.dto.CommentDTO;
import com.vanessamatos.springBootMongoDB.repository.PostRepository;
import com.vanessamatos.springBootMongoDB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userReposiroty;

    @Autowired
    private PostRepository postReposiroty;
    @Override
    public void run(String... arg0) throws Exception {


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userReposiroty.deleteAll();
        postReposiroty.deleteAll();

        User maria = new User(null, "Maria Antonia", "maria@gmail.com");
        User bianca = new User(null, "Bianca Andrade", "bianca@gmail.com");
        User fabricio = new User(null, "Fabricio Mendes", "fabricio@gmail.com");

        userReposiroty.saveAll(Arrays.asList(maria, bianca, fabricio));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(bianca));
        CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(fabricio));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(bianca));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postReposiroty.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userReposiroty.save(maria);
    }
}
