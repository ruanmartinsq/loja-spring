package com.nubank.happynation.controller;

import com.nubank.happynation.dto.ProdutoDTO;
import com.nubank.happynation.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/produtos")
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> save(@RequestBody ProdutoDTO produto) {
        ProdutoDTO prodSaved = service.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prodSaved);
    }

    @DeleteMapping({"id"})
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
