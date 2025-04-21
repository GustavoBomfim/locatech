package br.com.locatech.locatech.repositories;

import br.com.locatech.locatech.dtos.ReservaRequestDTO;

import java.math.BigDecimal;

public interface ReservaRepository {
    Integer saveReserva(ReservaRequestDTO reservaDTO, BigDecimal valorEstimado);
}
