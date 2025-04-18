package br.com.locatech.locatech.services;

import br.com.locatech.locatech.dtos.AluguelRequestDTO;
import br.com.locatech.locatech.entities.Aluguel;

import java.util.List;
import java.util.Optional;

public interface AluguelService {

    List<Aluguel> findAllAlugueis(int page, int size);

    Optional<Aluguel> findAluguelById(Long id);

    void saveAluguel(AluguelRequestDTO aluguel);

    void updateAluguel(Aluguel aluguel, Long id);

    void delete(Long id);
}
