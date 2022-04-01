package com.example.demo.service.helper;

import com.example.demo.dto.FormaPagamentoDto;
import com.example.demo.dto.request.DescricaoRequestDto;
import com.example.demo.dto.request.TransacaoRequestDto;
import com.example.demo.dto.response.DescricaoResponseDto;
import com.example.demo.dto.response.TransacaoResponseDto;
import com.example.demo.entity.DescricaoEntity;
import com.example.demo.entity.FormaPagamentoEntity;
import com.example.demo.entity.Status;
import com.example.demo.entity.TipoPagamento;
import com.example.demo.entity.TransacaoEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.example.demo.entity.TipoPagamento.AVISTA;

public class TransacaoHelper {
    public static final Long ID = 1L;
    public static final String CARTAO = "cartao";
    public static final Integer PARCELAS = 1;
    public static final LocalDateTime _2022_03_31_12_00 = LocalDateTime.of(2022, 3, 31, 12, 0, 12);
    public static final String ESTABELECIMENTO = "estabelecimento";
    public static final BigDecimal VALOR = BigDecimal.TEN;
    public static final TipoPagamento TIPO = AVISTA;
    public static final String NSU = "nsu";
    public static final Status AUTORIZADO = Status.AUTORIZADO;
    public static final String CODIGO_DE_AUTORIZACAO = "codigo de autorizacao";

    public static TransacaoResponseDto novoPagamentoResponse() {
        TransacaoResponseDto pojo = new TransacaoResponseDto();
        pojo.setId(ID);
        pojo.setCartao(CARTAO);

        FormaPagamentoDto formaPagamento = new FormaPagamentoDto();
        formaPagamento.setParcelas(PARCELAS);
        formaPagamento.setTipo(TIPO);
        pojo.setFormaPagamento(formaPagamento);

        DescricaoResponseDto descricao = new DescricaoResponseDto();
        descricao.setNsu(NSU);
        descricao.setStatus(AUTORIZADO);
        descricao.setCodigoAutorizacao(CODIGO_DE_AUTORIZACAO);
        descricao.setDataHora(_2022_03_31_12_00);
        descricao.setEstabelecimento(ESTABELECIMENTO);
        descricao.setValor(VALOR);
        pojo.setDescricao(descricao);

        return pojo;
    }

    public static TransacaoRequestDto novoPagamentoRequest() {
        TransacaoRequestDto pojo = new TransacaoRequestDto();
        pojo.setId(ID);
        pojo.setCartao(CARTAO);

        FormaPagamentoDto formaPagamento = new FormaPagamentoDto();
        formaPagamento.setParcelas(PARCELAS);
        formaPagamento.setTipo(TIPO);
        pojo.setFormaPagamento(formaPagamento);

        DescricaoRequestDto descricao = new DescricaoRequestDto();
        descricao.setDataHora(_2022_03_31_12_00);
        descricao.setEstabelecimento(ESTABELECIMENTO);
        descricao.setValor(VALOR);
        pojo.setDescricao(descricao);

        return pojo;
    }

    public static TransacaoEntity novoPagamento() {
        TransacaoEntity pojo = new TransacaoEntity();
        pojo.setId(ID);
        pojo.setCartao(CARTAO);

        FormaPagamentoEntity formaPagamento = new FormaPagamentoEntity();
        formaPagamento.setId(ID);
        formaPagamento.setParcelas(PARCELAS);
        formaPagamento.setTipo(TIPO);
        pojo.setFormaPagamento(formaPagamento);

        DescricaoEntity descricao = new DescricaoEntity();
        descricao.setId(ID);
        descricao.setNsu(NSU);
        descricao.setStatus(AUTORIZADO);
        descricao.setCodigoAutorizacao(CODIGO_DE_AUTORIZACAO);
        descricao.setDataHora(_2022_03_31_12_00);
        descricao.setEstabelecimento(ESTABELECIMENTO);
        descricao.setValor(VALOR);
        pojo.setDescricao(descricao);

        return pojo;
    }
}
