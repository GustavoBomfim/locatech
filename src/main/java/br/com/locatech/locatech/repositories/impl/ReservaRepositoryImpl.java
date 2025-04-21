package br.com.locatech.locatech.repositories.impl;

import br.com.locatech.locatech.dtos.ReservaRequestDTO;
import br.com.locatech.locatech.repositories.ReservaRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

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
                .param("status", "Reservado")
                .param("valor_estimado", valorEstimado)
                .update();
    }
}
