package br.com.locatech.locatech.entities;

import br.com.locatech.locatech.entities.enums.ReservaStatus;
import br.com.locatech.locatech.exceptions.CancelamentoInvalidoException;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Reserva {
    private Long id;
    private Long pessoaId;
    private Long veiculoId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private ReservaStatus status;
    private BigDecimal valorEstimado;
    private LocalDate criadoEm;

    public void validarCancelamento(){
        if (!this.status.equals(ReservaStatus.RESERVADO)) {
            throw new CancelamentoInvalidoException("Reservas já canceladas ou concluídas não podem ser canceladas.");
        }

        long dias = ChronoUnit.DAYS.between(LocalDate.now(), this.dataInicio);
        if (dias < 1) {
            throw new CancelamentoInvalidoException("A reserva só pode ser cancelada com mais de 1 dia de antecedência.");
        }
    }

}
