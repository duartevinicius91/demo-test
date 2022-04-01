package com.example.demo.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DescricaoRequestDto {

    private BigDecimal valor;
    private LocalDateTime dataHora;
    private String estabelecimento;

}
