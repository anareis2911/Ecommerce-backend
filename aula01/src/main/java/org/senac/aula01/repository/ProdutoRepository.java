package org.senac.aula01.repository;

import java.util.List;

import org.senac.aula01.model.Produto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

    List<Produto> findByNomeContainingIgnoreCase(String filter, Sort s);
    
}
