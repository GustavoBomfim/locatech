package br.com.locatech.locatech.controllers;

import br.com.locatech.locatech.dtos.AluguelRequestDTO;
import br.com.locatech.locatech.entities.Aluguel;
import br.com.locatech.locatech.services.AluguelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alugueis")
@Tag(name = "Aluguel", description = "Controller para crud de aluguel")
public class AluguelController {

    private static final Logger logger = LoggerFactory.getLogger(AluguelController.class);


    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }


    @Operation(summary = "Busca todos os alugueis paginados")
    @GetMapping
    public ResponseEntity<List<Aluguel>> findAllAlugueis(@RequestParam("page") int page, @RequestParam("size") int size) {
        logger.info("findAll -> /alugueis");
        var alugueis = aluguelService.findAllAlugueis(page, size);
        return ResponseEntity.ok(alugueis);
    }

    @Operation(summary = "Busca aluguel pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluguel>> findAluguel(@PathVariable Long id) {
        logger.info("/alugueis/" + id);
        var aluguel = aluguelService.findAluguelById(id);
        return ResponseEntity.ok(aluguel);
    }

    @Operation(summary = "Cadastra aluguel")
    @PostMapping
    public ResponseEntity<Void> saveAluguel(@Valid @RequestBody AluguelRequestDTO aluguel) {
        logger.info("POST -> /alugueis");
        aluguelService.saveAluguel(aluguel);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Atualiza aluguel filtrado por id")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel(@PathVariable Long id, @RequestBody Aluguel aluguel) {
        logger.info("PUT -> /alugueis/" + id);
        aluguelService.updateAluguel(aluguel, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @Operation(summary = "Deleta aluguel filtrado por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluguel(@PathVariable Long id) {
        logger.info("Delete -> /alugueis/" + id);
        aluguelService.delete(id);
        return ResponseEntity.ok().build();
    }
}
