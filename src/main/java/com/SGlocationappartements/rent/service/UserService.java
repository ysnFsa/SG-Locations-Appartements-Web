package com.SGlocationappartements.rent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.SGlocationappartements.rent.entity.User;
import com.SGlocationappartements.rent.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Long> getAllUserIds() {
        return userRepository.findAll().stream()
                .map(User::getId)
                .collect(Collectors.toList());
    }
}
