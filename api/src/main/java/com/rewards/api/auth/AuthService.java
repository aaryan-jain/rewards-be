package com.rewards.api.auth;

import com.rewards.api.auth.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    private final IUserRepository IUserRepository;

    @Autowired
    AuthService(final IUserRepository IUserRepository) {
        this.IUserRepository = IUserRepository;
    }

    public List<UserDetailsDto> getListOfAllUsers() {
        Iterable<UserEntity> list = IUserRepository.findAll();
        List<UserDetailsDto> returnList = new ArrayList<>();
        list.forEach(item -> {
            returnList.add(new UserDetailsDto(item.getEmail(), item.getUserid()));
        });
        return returnList;
    }
}
