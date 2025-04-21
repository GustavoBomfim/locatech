package br.com.locatech.locatech.dtos;

import java.time.LocalDate;

public record ReservaRequestDTO(
        Long pessoaId,
        Long veiculoId,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}
