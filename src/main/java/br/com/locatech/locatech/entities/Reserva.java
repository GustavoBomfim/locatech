package br.com.locatech.locatech.entities;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    private String status; //reservado, cancelado, concluído
    private BigDecimal valorEstimado;
    private LocalDate dataDeCriacao;


}
