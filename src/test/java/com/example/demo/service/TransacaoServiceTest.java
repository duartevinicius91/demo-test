package com.example.demo.service;

import com.example.demo.dto.response.TransacaoResponseDto;
import com.example.demo.entity.TransacaoEntity;
import com.example.demo.repository.TransacaoRepository;
import com.example.demo.service.helper.TransacaoHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(PER_CLASS)
class TransacaoServiceTest {

    private TransacaoService transacaoService;
    private TransacaoRepository transacaoRepository;

    @BeforeAll
    void beforeAll() {
        transacaoRepository = mock(TransacaoRepository.class);
        transacaoService = new TransacaoService(transacaoRepository, new ModelMapper());
    }

    @Test
    void deveRetornarNovoPagamento() {
        when(transacaoRepository.save(any(TransacaoEntity.class)))
            .thenReturn(TransacaoHelper.novoPagamento());

        TransacaoResponseDto response = transacaoService.novoPagamento(TransacaoHelper.novoPagamentoRequest());

        assertNotNull(response);
        assertEquals(response.getId(), TransacaoHelper.ID);
        assertEquals(response.getCartao(), TransacaoHelper.CARTAO);
        assertEquals(response.getDescricao().getCodigoAutorizacao(), TransacaoHelper.CODIGO_DE_AUTORIZACAO);
        assertEquals(response.getDescricao().getDataHora(), TransacaoHelper._2022_03_31_12_00);
        assertEquals(response.getDescricao().getNsu(), TransacaoHelper.NSU);
        assertEquals(response.getDescricao().getEstabelecimento(), TransacaoHelper.ESTABELECIMENTO);
        assertEquals(response.getDescricao().getValor(), TransacaoHelper.VALOR);
        assertEquals(response.getDescricao().getStatus(), TransacaoHelper.AUTORIZADO);
        assertEquals(response.getFormaPagamento().getParcelas(), TransacaoHelper.PARCELAS);
        assertEquals(response.getFormaPagamento().getTipo(), TransacaoHelper.TIPO);
    }


}