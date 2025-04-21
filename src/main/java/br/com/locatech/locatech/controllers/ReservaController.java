package br.com.locatech.locatech.controllers;

import br.com.locatech.locatech.dtos.ReservaRequestDTO;
import br.com.locatech.locatech.services.ReservaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



}
