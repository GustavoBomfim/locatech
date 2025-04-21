package br.com.locatech.locatech.repositories;

import br.com.locatech.locatech.dtos.ReservaRequestDTO;
import br.com.locatech.locatech.entities.Reserva;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository {
    Integer saveReserva(ReservaRequestDTO reservaDTO, BigDecimal valorEstimado);

    Optional<Reserva> findById(Long idReserva);

    List<Reserva> findAll();

    Integer cancelarReserva(Long idReserva);
}
