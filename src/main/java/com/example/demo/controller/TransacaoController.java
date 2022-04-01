package com.example.demo.controller;

import com.example.demo.dto.request.TransacaoRequestDto;
import com.example.demo.dto.response.TransacaoResponseDto;
import com.example.demo.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
@Validated
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<TransacaoResponseDto> novoPagamento(@RequestBody TransacaoRequestDto transacaoRequestDto, UriComponentsBuilder uriComponentsBuilder) {
        TransacaoResponseDto responseDto = transacaoService.novoPagamento(transacaoRequestDto);
        URI location = uriComponentsBuilder.path("/transacao/{id}").buildAndExpand(responseDto.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity<>(responseDto, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{transacaoId}/estorno")
    public TransacaoResponseDto estornaPagamento(@PathVariable @Min(1) Long transacaoId) {
        return transacaoService.estornaPagamento(transacaoId);
    }

    @GetMapping("/{transacaoId}/estorno")
    public TransacaoResponseDto consultaEstorno(@PathVariable @Min(1) Long transacaoId) {
        return transacaoService.consultaEstorno(transacaoId);
    }

    @GetMapping
    public List<TransacaoResponseDto> findAll() {
        return transacaoService.findAll();
    }

    @GetMapping("/{transacaoId}")
    public TransacaoResponseDto findById(@PathVariable @Min(1) Long transacaoId) {
        return transacaoService.findById(transacaoId);
    }

}
