package com.TLBTECh.proj_bff_agendador_tarefas.infrastructure.client.config;

import com.TLBTECh.proj_bff_agendador_tarefas.infrastructure.exceptions.*;
import com.TLBTECh.proj_bff_agendador_tarefas.infrastructure.exceptions.IllegalArgumentException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeingError implements ErrorDecoder {

    // Constante para prefixo de mensagens de erro
    private static final String ERROR_PREFIX = "Erro: ";

    @Override
    public Exception decode(String s, Response response) {
        String mensagemErro;
        try {
            mensagemErro = mensagemErro(response);
        } catch (IOException e) {
            return new BusinessException(ERROR_PREFIX + "ao ler corpo da resposta", e);
        }

        // Switch simples com inteiros (compatível com Java 17)
        switch (response.status()) {
            case 409:
                return new ConflictException(ERROR_PREFIX + mensagemErro);
            case 403:
                return new ForbiddenException(ERROR_PREFIX + mensagemErro);
            case 404:
                return new ResourceNotFoundException(ERROR_PREFIX + mensagemErro);
            case 401:
                return new UnauthorizedException(ERROR_PREFIX + mensagemErro);
            case 400:
                return new IllegalArgumentException(ERROR_PREFIX + mensagemErro);
            default:
                return new BusinessException(ERROR_PREFIX + mensagemErro);
        }
    }

    private String mensagemErro(Response response) throws IOException {
        if (Objects.isNull(response.body())) {
            return "";
        }
        return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}

