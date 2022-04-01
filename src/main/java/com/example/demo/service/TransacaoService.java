package com.example.demo.service;

import com.example.demo.dto.request.TransacaoRequestDto;
import com.example.demo.dto.response.TransacaoResponseDto;
import com.example.demo.entity.DescricaoEntity;
import com.example.demo.entity.Status;
import com.example.demo.entity.TransacaoEntity;
import com.example.demo.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    public static final String TRANSACAO_NAO_ENCONTRADA = "Transacao %d não encontrada";
    private final TransacaoRepository transacaoRepository;
    private final ModelMapper modelMapper;

    public TransacaoResponseDto novoPagamento(TransacaoRequestDto transacaoRequest) {

        TransacaoEntity transacaoEntity = modelMapper.map(transacaoRequest, TransacaoEntity.class);

        DescricaoEntity descricao = transacaoEntity.getDescricao();
        descricao.setStatus(Status.AUTORIZADO);
        descricao.setCodigoAutorizacao(UUID.randomUUID().toString());
        descricao.setNsu(UUID.randomUUID().toString());

        transacaoEntity = transacaoRepository.save(transacaoEntity);

        return modelMapper.map(transacaoEntity, TransacaoResponseDto.class);
    }

    public TransacaoResponseDto estornaPagamento(Long transacaoId) {
        try {
            TransacaoEntity transacaoEntity = transacaoRepository.getById(transacaoId);
            transacaoEntity.getDescricao().setStatus(Status.CANCELADO);
            transacaoEntity = transacaoRepository.save(transacaoEntity);
            return modelMapper.map(transacaoEntity, TransacaoResponseDto.class);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(format(TRANSACAO_NAO_ENCONTRADA, transacaoId));
        }
    }

    public TransacaoResponseDto consultaEstorno(Long transacaoId) {
        return transacaoRepository.findEstornoById(transacaoId)
                                  .map(entity -> modelMapper.map(entity, TransacaoResponseDto.class))
                                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estorno não localizado"));
    }

    public List<TransacaoResponseDto> findAll() {
        return transacaoRepository.findAll()
                                  .stream()
                                  .map(entity -> modelMapper.map(entity, TransacaoResponseDto.class))
                                  .collect(Collectors.toList());
    }

    public TransacaoResponseDto findById(Long transacaoId) {
        return transacaoRepository.findById(transacaoId)
                                  .map(transacaoEntity -> modelMapper.map(transacaoEntity, TransacaoResponseDto.class))
                                  .orElseThrow(() -> new ResourceNotFoundException(format(TRANSACAO_NAO_ENCONTRADA, transacaoId)));
    }
}
