package com.example.demo.controller;

import com.example.demo.service.helper.TransacaoHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.demo.service.helper.TransacaoHelper.novoPagamentoRequest;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TransacaoControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testIntegration() throws Exception {

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
               .andExpect(jsonPath("$.descricao.estabelecimento").value(TransacaoHelper.ESTABELECIMENTO))
               .andExpect(jsonPath("$.descricao.status").value(TransacaoHelper.AUTORIZADO.toString()))
        ;

        mockMvc.perform(get("/transacao/1")
                            .contentType("application/json"))
               .andExpect(status().isOk())

               .andExpect(jsonPath("$.id", is(1)))
               .andExpect(jsonPath("$.cartao", is(TransacaoHelper.CARTAO)))

               .andExpect(jsonPath("$.formaPagamento.parcelas", is(TransacaoHelper.PARCELAS)))
               .andExpect(jsonPath("$.formaPagamento.tipo", is(TransacaoHelper.TIPO.toString())))

               .andExpect(jsonPath("$.descricao.valor").value(TransacaoHelper.VALOR))
               .andExpect(jsonPath("$.descricao.estabelecimento").value(TransacaoHelper.ESTABELECIMENTO))
               .andExpect(jsonPath("$.descricao.status").value(TransacaoHelper.AUTORIZADO.toString()))
        ;

    }
}