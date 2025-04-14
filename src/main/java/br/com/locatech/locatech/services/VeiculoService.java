package br.com.locatech.locatech.services;

import br.com.locatech.locatech.entities.Veiculo;

import java.util.List;
import java.util.Optional;

public interface VeiculoService {

    List<Veiculo> findAllVeiculos(int page, int size);

    Optional<Veiculo> findVeiculoById(Long id);

    void saveVeiculo(Veiculo veiculo);

    void updateVeiculo(Veiculo veiculo, Long id);

    void delete(Long id);


}

