package com.example.demo.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
public class TransacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 30)
    private String cartao;

    @OneToOne(cascade = CascadeType.ALL)
    private DescricaoEntity descricao;

    @OneToOne(cascade = CascadeType.ALL)
    private FormaPagamentoEntity formaPagamento;
}
