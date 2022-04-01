package com.example.demo.dto;

import com.example.demo.entity.TipoPagamento;
import lombok.Data;

@Data
public class FormaPagamentoDto {

    private TipoPagamento tipo;
    private Integer parcelas;
}
