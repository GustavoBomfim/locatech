package br.com.locatech.locatech.services.impl;

import br.com.locatech.locatech.dtos.AluguelRequestDTO;
import br.com.locatech.locatech.entities.Aluguel;
import br.com.locatech.locatech.exceptions.ResourceNotFoundException;
import br.com.locatech.locatech.repositories.AluguelRepository;
import br.com.locatech.locatech.repositories.VeiculoRepository;
import br.com.locatech.locatech.services.AluguelService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelServiceImpl implements AluguelService {

    private final AluguelRepository aluguelRepository;
    private final VeiculoRepository veiculoRepository;

    public AluguelServiceImpl(AluguelRepository aluguelRepository, VeiculoRepository veiculoRepository) {
        this.aluguelRepository = aluguelRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public List<Aluguel> findAllAlugueis(int page, int size) {
        int offset = (page - 1) * size;
        return this.aluguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findAluguelById(Long id) {
        return Optional.ofNullable(this.aluguelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado")));
    }

    public void saveAluguel(AluguelRequestDTO aluguel) {
        var aluguelEntity = calculaAluguel(aluguel);
        var save = this.aluguelRepository.save(aluguelEntity);
        Assert.state(save == 1, "Erro ao salvar " + aluguel.pessoaId());
    }

    public void updateAluguel(Aluguel aluguel, Long id) {
        var update = this.aluguelRepository.update(aluguel, id);
        if (update == 0) {
            throw new ResourceNotFoundException("Aluguel não encontrado");
        }

    }

    public void delete(Long id) {
        var delete = this.aluguelRepository.delete(id);
        if (delete == 0) {
            throw new ResourceNotFoundException("Aluguel não encontrado");
        }
    }

    private Aluguel calculaAluguel(AluguelRequestDTO aluguel) {
        var veiculo = this.veiculoRepository.findById(aluguel.veiculoId())
                .orElseThrow(() -> new ResourceNotFoundException("Véiculo não encontrado"));

        var quantidadeDiasAlugados = BigDecimal.valueOf(aluguel.dataFim().getDayOfYear() - aluguel.dataInicio().getDayOfYear());
        var valor = veiculo.getValorDiaria().multiply(quantidadeDiasAlugados);
        return new Aluguel(aluguel, valor);
    }
}
