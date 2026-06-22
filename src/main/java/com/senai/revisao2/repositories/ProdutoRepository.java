package com.senai.revisao2.repositories;

import com.senai.revisao2.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    boolean existsByNomeProduto(String nomeProduto);

    Optional<ProdutoEntity> findById(Long id);
}
