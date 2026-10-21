package com.nubank.happynation.controller;

import com.nubank.happynation.dto.ClienteDTO;
import com.nubank.happynation.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService service;


    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrar(@RequestBody ClienteDTO cliente) {
        ClienteDTO clienteSaved = service.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSaved);
    }
}
