package com.cahier.backend.services;

import com.cahier.backend.entities.User;
import com.cahier.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // Méthode pour vérifier si l'email existe déjà
    public boolean isEmailExist(String email) {
        return userRepository.findByMail(email).isPresent();
    }

    // Méthode pour vérifier si le numéro de téléphone existe déjà
    public boolean isPhoneNumberExist(String phoneNumber) {
        return userRepository.findByNumeroTelephone(phoneNumber).isPresent();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        // Vérification de l'existence de l'email ou du numéro de téléphone
        if (isEmailExist(user.getMail())) {
            throw new DataIntegrityViolationException("L'email est déjà utilisé");
        }

        if (isPhoneNumberExist(user.getNumeroTelephone())) {
            throw new DataIntegrityViolationException("Le numéro de téléphone est déjà utilisé");
        }

        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
