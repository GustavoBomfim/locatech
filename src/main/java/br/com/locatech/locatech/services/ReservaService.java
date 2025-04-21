package br.com.locatech.locatech.services;

import br.com.locatech.locatech.dtos.ReservaRequestDTO;

public interface ReservaService {
    void saveReserva(ReservaRequestDTO reservaDTO);
}
