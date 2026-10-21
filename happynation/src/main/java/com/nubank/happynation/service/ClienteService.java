package com.nubank.happynation.service;

import com.nubank.happynation.dto.ClienteDTO;
import com.nubank.happynation.model.Cliente;
import com.nubank.happynation.model.Produto;
import com.nubank.happynation.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(cliente -> new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getSaldo()))
                .toList();
    }

    public ClienteDTO salvar(ClienteDTO dto) {
        Cliente novoCliente = new Cliente(dto.nome(), dto.cpf(), dto.saldo());

        Cliente clientsSalvo = repository.save(novoCliente);
        return new ClienteDTO(clientsSalvo.getId(), clientsSalvo.getNome(), clientsSalvo.getCpf(), clientsSalvo.getSaldo());
    }
}
