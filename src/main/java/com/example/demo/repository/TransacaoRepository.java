package com.example.demo.repository;

import com.example.demo.entity.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Long> {

    @Query("SELECT t FROM TransacaoEntity t WHERE t.descricao.id = :transacaoId and t.descricao.status = 'CANCELADO'")
    Optional<TransacaoEntity> findEstornoById(@Param("transacaoId") Long transacaoId);
}
