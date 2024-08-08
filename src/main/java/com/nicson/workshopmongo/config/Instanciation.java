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

        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        fmt.setTimeZone(TimeZone.getTimeZone("GMT"));

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        Post post1 = new Post(null, fmt.parse("21/03/2018"), "Partiu Viagem!", "Estou indo viajar para Londres", maria);
        Post post2 = new Post(null, fmt.parse("09/09/2020"), "Indo Trabalhar",
                "Hoje o caminho do trabalho estava lindo", alex);

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        postRepository.saveAll(Arrays.asList(post1, post2));
    }

}
