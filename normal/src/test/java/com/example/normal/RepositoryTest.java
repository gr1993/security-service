package com.example.normal;

import com.example.normal.entity.User;
import com.example.normal.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("park");
        user.setPassword("password");
        user.setAge(20);
        userRepository.save(user);
    }
}
