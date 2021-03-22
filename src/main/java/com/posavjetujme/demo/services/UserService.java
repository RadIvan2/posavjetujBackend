package com.posavjetujme.demo.services;

import com.posavjetujme.demo.config.SecurityConfiguration;
import com.posavjetujme.demo.domains.Question;
import com.posavjetujme.demo.domains.User;
import com.posavjetujme.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public User createUser(User user){
        String pswd=user.getPassword();
        //String encodedString = Base64.getEncoder().encodeToString(pswd.getBytes());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(pswd);
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User updateUser(Integer id,User user) {
            Optional<User> userOptional = userRepository.findById(id);
            if(userOptional.isPresent()){
                User userToBeUpdated = userOptional.get();
                if(user.getUsername()!=null){
                    userToBeUpdated.setUsername(user.getUsername());}
                if(user.getPassword()!=null){
                    String pswd=user.getPassword();
                    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    String encodedPassword = passwordEncoder.encode(pswd);
                    userToBeUpdated.setPassword(encodedPassword);}
                if(user.getName()!=null){
                userToBeUpdated.setName(user.getName());}
                if(user.getSurname()!=null){
                userToBeUpdated.setSurname(user.getSurname());}
                if(user.getEmail()!=null){
                userToBeUpdated.setEmail(user.getEmail());}
                return userRepository.save(userToBeUpdated);
            }
            return null;

    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
