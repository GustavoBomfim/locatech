package br.com.locatech.locatech.services;

import br.com.locatech.locatech.entities.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

    List<Pessoa> findAllPessoas(int page, int size);

    Optional<Pessoa> findPessoaById(Long id);

    void savePessoa(Pessoa pessoa);

    void updatePessoa(Pessoa pessoa, Long id);

    void delete(Long id);
}
