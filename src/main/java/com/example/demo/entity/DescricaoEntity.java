package com.example.demo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class DescricaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin("0.01")
    private BigDecimal valor;

    private LocalDateTime dataHora;

    @NotBlank
    private String estabelecimento;

    @NotBlank
    private String nsu;

    @NotBlank
    private String codigoAutorizacao;

    @NotNull
    private Status status;
}
