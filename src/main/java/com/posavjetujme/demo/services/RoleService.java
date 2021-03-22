package com.posavjetujme.demo.services;

import com.posavjetujme.demo.domains.Role;
import com.posavjetujme.demo.repositories.RoleRepository;
import com.posavjetujme.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {


    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }
}
