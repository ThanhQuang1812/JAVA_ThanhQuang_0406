package com.example.java_thanhquang_0406.Services;
import com.example.java_thanhquang_0406.Entities.User;
import com.example.java_thanhquang_0406.Repositories.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    // Retrieve all products from the database
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    // Add a new product to the database
    public User addUser(User user) {
        return userRepository.save(user);
    }
    // Update an existing product
    public User updateUser(@NotNull User user) {
        User existingUser =  userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalStateException("User with ID " +
                        user.getId() + " does not exist."));
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setBirthDay(user.getBirthDay());
        existingUser.setDeleted(user.isDeleted());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }
    // Delete a product by its id
    public void deleteUserById(Long id) {
            if (!userRepository.existsById(id)) {
            throw new IllegalStateException("User with ID " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }
}
