package br.com.locatech.locatech.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequestDTO(
        @NotNull(message = "O id da pessoa não pode ser nulo")
        Long pessoaId,
        @NotNull(message = "O id do véiculo não pode ser nulo")
        Long veiculoId,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}
