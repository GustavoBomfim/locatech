package br.com.locatech.locatech.controllers;

import br.com.locatech.locatech.entities.Veiculo;
import br.com.locatech.locatech.services.VeiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> findAllVeiculos(@RequestParam("page") int page, @RequestParam("size") int size) {
        logger.info("findAll -> /veiculos");
        var veiculos = veiculoService.findAllVeiculos(page, size);
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veiculo>> findVeiculo(@PathVariable Long id) {
        logger.info("/veiculos/" + id);
        var veiculo = veiculoService.findVeiculoById(id);
        return ResponseEntity.ok(veiculo);
    }

    @PostMapping
    public ResponseEntity<Void> saveVeiculo(@RequestBody Veiculo veiculo) {
        logger.info("POST -> /veiculos");
        veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        logger.info("PUT -> /veiculos/" + id);
        veiculoService.updateVeiculo(veiculo, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id){
        logger.info("Delete -> /veiculos/" + id);
        veiculoService.delete(id);
        return ResponseEntity.ok().build();
    }


}
