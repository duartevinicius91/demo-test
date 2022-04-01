package com.example.demo.controller;

import com.example.demo.dto.request.TransacaoRequestDto;
import com.example.demo.service.TransacaoService;
import com.example.demo.service.helper.TransacaoHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.example.demo.service.helper.TransacaoHelper.novoPagamentoRequest;
import static com.example.demo.service.helper.TransacaoHelper.novoPagamentoResponse;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransacaoController.class)
public class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransacaoService transacaoService;

    @Test
    void novoPagamento() throws Exception {
        when(transacaoService.novoPagamento(any(TransacaoRequestDto.class)))
            .thenReturn(novoPagamentoResponse());

        mockMvc.perform(post("/transacao")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(novoPagamentoRequest())))
               .andExpect(status().isCreated())
               .andExpect(header().string(HttpHeaders.LOCATION, containsString("/transacao/" + TransacaoHelper.ID)))
               .andExpect(jsonPath("$.id", is(1)))
               .andExpect(jsonPath("$.cartao", is(TransacaoHelper.CARTAO)))

               .andExpect(jsonPath("$.formaPagamento.parcelas", is(TransacaoHelper.PARCELAS)))
               .andExpect(jsonPath("$.formaPagamento.tipo", is(TransacaoHelper.TIPO.toString())))

               .andExpect(jsonPath("$.descricao.valor").value(TransacaoHelper.VALOR))
               .andExpect(jsonPath("$.descricao.dataHora").value(TransacaoHelper._2022_03_31_12_00.toString()))
               .andExpect(jsonPath("$.descricao.estabelecimento").value(TransacaoHelper.ESTABELECIMENTO))
               .andExpect(jsonPath("$.descricao.nsu").value(TransacaoHelper.NSU))
               .andExpect(jsonPath("$.descricao.codigoAutorizacao").value(TransacaoHelper.CODIGO_DE_AUTORIZACAO))
               .andExpect(jsonPath("$.descricao.status").value(TransacaoHelper.AUTORIZADO.toString()))
               ;
    }

    @Test
    void estornaPagamento() throws Exception {
        when(transacaoService.estornaPagamento(anyLong()))
            .thenReturn(novoPagamentoResponse());

        mockMvc.perform(put("/transacao/1/estorno")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(novoPagamentoRequest())))
               .andExpect(status().isOk())

               .andExpect(jsonPath("$.id", is(1)))
               .andExpect(jsonPath("$.cartao", is(TransacaoHelper.CARTAO)))

               .andExpect(jsonPath("$.formaPagamento.parcelas", is(TransacaoHelper.PARCELAS)))
               .andExpect(jsonPath("$.formaPagamento.tipo", is(TransacaoHelper.TIPO.toString())))

               .andExpect(jsonPath("$.descricao.valor").value(TransacaoHelper.VALOR))
               .andExpect(jsonPath("$.descricao.dataHora").value(TransacaoHelper._2022_03_31_12_00.toString()))
               .andExpect(jsonPath("$.descricao.estabelecimento").value(TransacaoHelper.ESTABELECIMENTO))
               .andExpect(jsonPath("$.descricao.nsu").value(TransacaoHelper.NSU))
               .andExpect(jsonPath("$.descricao.codigoAutorizacao").value(TransacaoHelper.CODIGO_DE_AUTORIZACAO))
               .andExpect(jsonPath("$.descricao.status").value(TransacaoHelper.AUTORIZADO.toString()))

        ;
    }

    @Test
    void consultaEstorno() throws Exception {
        when(transacaoService.consultaEstorno(anyLong()))
            .thenReturn(novoPagamentoResponse());

        mockMvc.perform(get("/transacao/1/estorno")
                            .contentType("application/json"))
               .andExpect(status().isOk())

               .andExpect(jsonPath("$.id", is(1)))
               .andExpect(jsonPath("$.cartao", is(TransacaoHelper.CARTAO)))

               .andExpect(jsonPath("$.formaPagamento.parcelas", is(TransacaoHelper.PARCELAS)))
               .andExpect(jsonPath("$.formaPagamento.tipo", is(TransacaoHelper.TIPO.toString())))

               .andExpect(jsonPath("$.descricao.valor").value(TransacaoHelper.VALOR))
               .andExpect(jsonPath("$.descricao.dataHora").value(TransacaoHelper._2022_03_31_12_00.toString()))
               .andExpect(jsonPath("$.descricao.estabelecimento").value(TransacaoHelper.ESTABELECIMENTO))
               .andExpect(jsonPath("$.descricao.nsu").value(TransacaoHelper.NSU))
               .andExpect(jsonPath("$.descricao.codigoAutorizacao").value(TransacaoHelper.CODIGO_DE_AUTORIZACAO))
               .andExpect(jsonPath("$.descricao.status").value(TransacaoHelper.AUTORIZADO.toString()))

        ;
    }

    @Test
    void findAll() throws Exception {
        when(transacaoService.findAll())
            .thenReturn(Arrays.asList(novoPagamentoResponse()));

        mockMvc.perform(get("/transacao")
                            .contentType("application/json"))
               .andExpect(status().isOk())

               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].id", is(1)))
               .andExpect(jsonPath("$[0]cartao", is(TransacaoHelper.CARTAO)))

               .andExpect(jsonPath("$[0]formaPagamento.parcelas", is(TransacaoHelper.PARCELAS)))
               .andExpect(jsonPath("$[0]formaPagamento.tipo", is(TransacaoHelper.TIPO.toString())))

               .andExpect(jsonPath("$[0]descricao.valor").value(TransacaoHelper.VALOR))
               .andExpect(jsonPath("$[0]descricao.dataHora").value(TransacaoHelper._2022_03_31_12_00.toString()))
               .andExpect(jsonPath("$[0]descricao.estabelecimento").value(TransacaoHelper.ESTABELECIMENTO))
               .andExpect(jsonPath("$[0]descricao.nsu").value(TransacaoHelper.NSU))
               .andExpect(jsonPath("$[0]descricao.codigoAutorizacao").value(TransacaoHelper.CODIGO_DE_AUTORIZACAO))
               .andExpect(jsonPath("$[0]descricao.status").value(TransacaoHelper.AUTORIZADO.toString()))

        ;
    }

    @Test
    void findById() throws Exception {
        when(transacaoService.findById(anyLong()))
            .thenReturn(novoPagamentoResponse());

        mockMvc.perform(get("/transacao/1")
                            .contentType("application/json"))
               .andExpect(status().isOk())

               .andExpect(jsonPath("$.id", is(1)))
               .andExpect(jsonPath("$.cartao", is(TransacaoHelper.CARTAO)))

               .andExpect(jsonPath("$.formaPagamento.parcelas", is(TransacaoHelper.PARCELAS)))
               .andExpect(jsonPath("$.formaPagamento.tipo", is(TransacaoHelper.TIPO.toString())))

               .andExpect(jsonPath("$.descricao.valor").value(TransacaoHelper.VALOR))
               .andExpect(jsonPath("$.descricao.dataHora").value(TransacaoHelper._2022_03_31_12_00.toString()))
               .andExpect(jsonPath("$.descricao.estabelecimento").value(TransacaoHelper.ESTABELECIMENTO))
               .andExpect(jsonPath("$.descricao.nsu").value(TransacaoHelper.NSU))
               .andExpect(jsonPath("$.descricao.codigoAutorizacao").value(TransacaoHelper.CODIGO_DE_AUTORIZACAO))
               .andExpect(jsonPath("$.descricao.status").value(TransacaoHelper.AUTORIZADO.toString()))

        ;
    }


}