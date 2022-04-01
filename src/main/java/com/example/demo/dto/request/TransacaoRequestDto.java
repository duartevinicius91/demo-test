package com.example.demo.dto.request;

import com.example.demo.dto.FormaPagamentoDto;
import lombok.Data;

@Data
public class TransacaoRequestDto {

    private Long id;
    private String cartao;
    private DescricaoRequestDto descricao;
    private FormaPagamentoDto formaPagamento;
}
