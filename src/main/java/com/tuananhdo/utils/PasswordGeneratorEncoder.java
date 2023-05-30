package com.tuananhdo.utils;

import com.tuananhdo.entity.User;
import com.tuananhdo.repository.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class PasswordGeneratorEncoder {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PasswordGeneratorEncoder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void encryptPasswordsForUsersExceptId(Long userId) {
        List<User> users = userRepository.findAllUsersExcept(userId);
        for (User user : users) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }
        userRepository.saveAll(users);
    }
}
