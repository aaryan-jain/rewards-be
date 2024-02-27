package com.rewards.api.User;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findByClerkId(String clerkId) {
        return userRepository.findByUserId(clerkId).orElseThrow(EntityNotFoundException::new);
    }

    public User updateUser(UpdateUserDto updateUserDto) {
        try {
            User usertobeupdated = findByClerkId(updateUserDto.getUserClerkId());
            usertobeupdated.changeFirstAndLastname(updateUserDto.getFirstName(), updateUserDto.getLastName());
            return this.saveUser(usertobeupdated);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e);
        }
    }
}
