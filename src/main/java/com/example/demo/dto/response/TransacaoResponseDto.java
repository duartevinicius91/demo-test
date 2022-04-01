package com.example.demo.dto.response;

import com.example.demo.dto.FormaPagamentoDto;
import lombok.Data;

@Data
public class TransacaoResponseDto {

    private Long id;
    private String cartao;
    private DescricaoResponseDto descricao;
    private FormaPagamentoDto formaPagamento;
}
