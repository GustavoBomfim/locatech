package br.com.locatech.locatech.services;

import br.com.locatech.locatech.dtos.ReservaRequestDTO;
import br.com.locatech.locatech.entities.Reserva;

public interface ReservaService {
    void saveReserva(ReservaRequestDTO reservaDTO);

    void cancelarReserva(Long idReserva);

    Reserva findReservaById(Long idReserva);
}
