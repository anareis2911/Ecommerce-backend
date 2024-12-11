package org.senac.aula01.controller;

import java.util.List;

import org.senac.aula01.model.Produto;
import org.senac.aula01.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

@GetMapping
public List<Produto> get(
        @RequestParam(required = false, defaultValue = "nome") String order,
        @RequestParam(required = false) String filter) {

    // Configura o tipo de ordenação baseado no parâmetro `order`
    Sort sort;
    switch (order) {
        case "valor_mais_alto":
            sort = Sort.by(Sort.Order.desc("preco"));
            break;
        case "valor_mais_baixo":
            sort = Sort.by(Sort.Order.asc("preco"));
            break;
        case "nome":
        default:
            sort = Sort.by(Sort.Order.asc("nome"));
            break;
    }

    // Se não há filtro, retorna todos os produtos ordenados
    if (filter == null || filter.isEmpty()) {
        return repository.findAll(sort);
    }

    // Se há filtro, retorna os produtos cujo nome contém o filtro (ignorando maiúsculas/minúsculas)
    return repository.findByNomeContainingIgnoreCase(filter, sort);
}


    @PostMapping
    public Produto save(@RequestBody Produto produto){
        return repository.save(produto);
    }
    
}