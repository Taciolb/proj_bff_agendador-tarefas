package com.TLBTECh.proj_bff_agendador_tarefas.infrastructure.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
