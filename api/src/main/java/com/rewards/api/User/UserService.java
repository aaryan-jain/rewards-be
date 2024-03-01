package com.rewards.api.User;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    private User saveUser(User user) {
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

    public User saveNewUser(CreateUserDto user) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(user.getFirstName().getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            User usertobesaved = new User(user, hexString);
            return userRepository.save(usertobesaved);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public User updateUser(UpdateUserPointsDto userPointsDto) {
        try {
            User usertobeupdated = findByClerkId(userPointsDto.getUserClerkId());
            usertobeupdated.changePoints(userPointsDto.getNewPoints());
            return this.saveUser(usertobeupdated);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e);
        }
    }
}
