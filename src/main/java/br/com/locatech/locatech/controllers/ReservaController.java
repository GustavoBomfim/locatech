package br.com.locatech.locatech.controllers;

import br.com.locatech.locatech.dtos.ReservaRequestDTO;
import br.com.locatech.locatech.entities.Reserva;
import br.com.locatech.locatech.services.ReservaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private static final Logger logger = LoggerFactory.getLogger(ReservaController.class);
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService){
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<Void> saveReserva(@RequestBody ReservaRequestDTO reservaDTO){
        reservaService.saveReserva(reservaDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idReserva}/cancelar")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long idReserva){
        reservaService.cancelarReserva(idReserva);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idReserva}")
    public ResponseEntity<Reserva> findReservaById(@PathVariable Long idReserva){
        Reserva reserva = reservaService.findReservaById(idReserva);
        return ResponseEntity.ok(reserva);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> findAllReservas(@RequestParam("page") int page, @RequestParam("size") int size){
        var reservas = reservaService.findAllReservas(page, size);
        return ResponseEntity.ok(reservas);
    }



}
