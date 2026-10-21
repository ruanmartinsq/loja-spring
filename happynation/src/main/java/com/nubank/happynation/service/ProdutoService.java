package com.nubank.happynation.service;

import com.nubank.happynation.dto.ProdutoDTO;
import com.nubank.happynation.model.Cliente;
import com.nubank.happynation.model.Produto;
import com.nubank.happynation.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto findByIdOrTrhowNotFound(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado"));
    }

    public List<ProdutoDTO> listAll() {
        return repository.findAll()
                .stream()
                .map(produto -> new ProdutoDTO(produto.getId(), produto.getPreco(), produto.getDescricao(), produto.getQuantidade()))
                .toList();
    }
    public ProdutoDTO save(ProdutoDTO dto) {
        Produto produtoNovo = new Produto(dto.preco(), dto.decricao(), dto.quantidade());

        Produto produtoSaved = repository.save(produtoNovo);
        return new ProdutoDTO(produtoSaved.getId(), produtoSaved.getPreco(), produtoSaved.getDescricao(), produtoSaved.getQuantidade());
    }

    public void delete(Long id) {
        var produto = findByIdOrTrhowNotFound(id);
        repository.delete(produto);
    }
}
