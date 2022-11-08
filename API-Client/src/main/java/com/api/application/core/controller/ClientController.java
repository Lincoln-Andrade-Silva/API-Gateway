package com.api.application.core.controller;

import com.api.application.core.model.Client;
import com.api.application.core.repository.ClientRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @Operation(
            summary = "Create a new Client",
            description = "Create a new Client"
    )
    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public Client create(
            @RequestBody Client bodyRequest
    ) {
        Client client = Client.builder().name(bodyRequest.getName()).cpf(bodyRequest.getCpf()).build();
        clientRepository.save(client);

        return client;
    }

    @Operation(
            summary = "List Clients",
            description = "List Clients"
    )
    @GetMapping(
            value = ""
    )
    public List<Client> list(

    ) {
        return clientRepository.findAll();
    }

    @Operation(
            summary = "Get Client by Id",
            description = "Get Client by Id"
    )
    @GetMapping(
            value = "{id}"
    )
    public Client get(
            @PathVariable(value = "id") Long id
    ) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

    @Operation(
            summary = "Delete Client by Id",
            description = "Delete Client by Id"
    )
    @DeleteMapping(
            value = "{id}"
    )
    public void delete(
            @PathVariable(value = "id") Long id
    ) {
        clientRepository.deleteById(id);
    }

}
