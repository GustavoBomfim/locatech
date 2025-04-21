package br.com.locatech.locatech.services;

import br.com.locatech.locatech.dtos.ReservaRequestDTO;
import br.com.locatech.locatech.entities.Reserva;

import java.util.List;

public interface ReservaService {
    void saveReserva(ReservaRequestDTO reservaDTO);

    void cancelarReserva(Long idReserva);

    Reserva findReservaById(Long idReserva);

    List<Reserva> findAllReservas(int page, int size);

}
