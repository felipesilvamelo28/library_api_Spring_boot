package com.felipe.library.controller;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.felipe.library.entity.Book;
import com.felipe.library.entity.Client;
import com.felipe.library.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/library/clients")
public class ClientController {

    @Autowired
    ClientRepository repository;

    @GetMapping
    List<Client> findAllClients(){
        return repository.findAll();
    }

    @PostMapping
    public Client saveClient(@RequestBody Client client){
        return repository.save(client);
    }

}
