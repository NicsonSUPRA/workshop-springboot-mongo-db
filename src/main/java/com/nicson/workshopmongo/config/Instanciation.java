package com.nicson.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nicson.workshopmongo.domains.Post;
import com.nicson.workshopmongo.domains.User;
import com.nicson.workshopmongo.dto.AuthorDTO;
import com.nicson.workshopmongo.dto.CommentDTO;
import com.nicson.workshopmongo.repositories.PostRepository;
import com.nicson.workshopmongo.repositories.UserRepository;

@Configuration
public class Instanciation implements CommandLineRunner {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PostRepository postRepository;

        @Override
        public void run(String... args) throws Exception {
                userRepository.deleteAll();

                postRepository.deleteAll();

                SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
                fmt.setTimeZone(TimeZone.getTimeZone("GMT"));

                User maria = new User(null, "Maria Brown", "maria@gmail.com");
                User alex = new User(null, "Alex Green", "alex@gmail.com");
                User bob = new User(null, "Bob Grey", "bob@gmail.com");

                userRepository.saveAll(Arrays.asList(maria, alex, bob));

                Post post1 = new Post(null, fmt.parse("21/03/2018"), "Partiu Viagem!", "Estou indo viajar para Londres",
                                new AuthorDTO(maria));
                CommentDTO commentm1 = new CommentDTO("Boa Viagem querida", fmt.parse("21/03/2018"),
                                new AuthorDTO(bob));
                CommentDTO commentm2 = new CommentDTO("Boa Viagem querida", fmt.parse("22/03/2018"),
                                new AuthorDTO(alex));

                Post post2 = new Post(null, fmt.parse("21/03/2018"), "Visita em París!",
                                "De Londres estamos indo em París",
                                new AuthorDTO(maria));

                Post post3 = new Post(null, fmt.parse("09/09/2020"), "Indo Trabalhar",
                                "Hoje o caminho do trabalho estava lindo", new AuthorDTO(alex));
                CommentDTO commenta1 = new CommentDTO("Sim, lhe vi no caminho", fmt.parse("21/03/2018"),
                                new AuthorDTO(bob));

                post1.getComments().addAll(Arrays.asList(commentm1, commentm1));
                post3.getComments().add(commenta1);

                postRepository.saveAll(Arrays.asList(post1, post2));

                maria.getPosts().add(post1);
                maria.getPosts().add(post2);
                userRepository.save(maria);
        }

}
