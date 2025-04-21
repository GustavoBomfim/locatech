package br.com.locatech.locatech.services.impl;

import br.com.locatech.locatech.dtos.ReservaRequestDTO;
import br.com.locatech.locatech.entities.Reserva;
import br.com.locatech.locatech.entities.Veiculo;
import br.com.locatech.locatech.exceptions.ResourceNotFoundException;
import br.com.locatech.locatech.repositories.ReservaRepository;
import br.com.locatech.locatech.repositories.VeiculoRepository;
import br.com.locatech.locatech.services.ReservaService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final VeiculoRepository veiculoRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository, VeiculoRepository veiculoRepository) {
        this.reservaRepository = reservaRepository;
        this.veiculoRepository = veiculoRepository;
    }

    @Override
    public void saveReserva(ReservaRequestDTO reservaDTO) {
        Optional<Veiculo> veiculo = veiculoRepository.findById(reservaDTO.veiculoId());
        var valorAluguel = calcularValorAluguel(reservaDTO.dataFim().getDayOfYear() - reservaDTO.dataInicio().getDayOfYear(), veiculo.get().getValorDiaria());
        var save = reservaRepository.saveReserva(reservaDTO, valorAluguel);
        Assert.state(save == 1, "Erro ao salvar " + reservaDTO.pessoaId());
    }

    @Override
    public void cancelarReserva(Long idReserva) {
        Reserva reserva = findReservaById(idReserva);
        reserva.validarCancelamento();
        reservaRepository.cancelarReserva(idReserva);
    }

    @Override
    public Reserva findReservaById(Long idReserva) {
        return reservaRepository.findById(idReserva)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva " + idReserva + " não encontrada"));
    }

    @Override
    public List<Reserva> findAllReservas(int page, int size) {
        int offset = (page - 1) * size;
        return reservaRepository.findAll(size, offset);
    }


    private BigDecimal calcularValorAluguel(Integer dias, BigDecimal valorDiaria) {
        return valorDiaria.multiply(new BigDecimal(dias));
    }
}
