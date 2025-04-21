package br.com.locatech.locatech.repositories.impl;

import br.com.locatech.locatech.dtos.ReservaRequestDTO;
import br.com.locatech.locatech.entities.Reserva;
import br.com.locatech.locatech.entities.enums.ReservaStatus;
import br.com.locatech.locatech.repositories.ReservaRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservaRepositoryImpl implements ReservaRepository {

    private final JdbcClient jdbcClient;

    public ReservaRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }



    @Override
    public Integer saveReserva(ReservaRequestDTO reservaDTO, BigDecimal valorEstimado){
        return jdbcClient.sql("INSERT INTO reservas (pessoa_id, veiculo_id, data_inicio, data_fim, status, valor_estimado) " +
                "VALUES (:pessoa_id, :veiculo_id, :data_inicio, :data_fim, :status, :valor_estimado)")
                .param("pessoa_id", reservaDTO.pessoaId())
                .param("veiculo_id", reservaDTO.veiculoId())
                .param("data_inicio", reservaDTO.dataInicio())
                .param("data_fim", reservaDTO.dataFim())
                .param("status", ReservaStatus.RESERVADO.name())
                .param("valor_estimado", valorEstimado)
                .update();
    }

    @Override
    public Optional<Reserva> findById(Long idReserva) {
        return jdbcClient.sql("SELECT * FROM reservas WHERE id = :idReserva")
                .param("idReserva", idReserva)
                .query(Reserva.class)
                .optional();
    }

    @Override
    public List<Reserva> findAll(int size, int offset){
        return jdbcClient.sql("SELECT * FROM reservas LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Reserva.class)
                .list();
    }

    @Override
    public Integer cancelarReserva(Long idReserva){
        return jdbcClient.sql("UPDATE reservas SET status = :status WHERE id = :idReserva")
                .param("status", ReservaStatus.CANCELADO.name())
                .param("idReserva", idReserva)
                .update();
    }
}
