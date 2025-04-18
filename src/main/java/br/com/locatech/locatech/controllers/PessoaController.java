package br.com.locatech.locatech.controllers;

import br.com.locatech.locatech.entities.Pessoa;
import br.com.locatech.locatech.services.PessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllPessoas(@RequestParam("page") int page, @RequestParam("size") int size) {
        logger.info("findAll -> /pessoas");
        var pessoas = pessoaService.findAllPessoas(page, size);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> findPessoa(@PathVariable Long id) {
        logger.info("/pessoas/" + id);
        var pessoa = pessoaService.findPessoaById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Void> savePessoa(@RequestBody Pessoa pessoa) {
        logger.info("POST -> /pessoas");
        pessoaService.savePessoa(pessoa);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        logger.info("PUT -> /pessoas/" + id);
        pessoaService.updatePessoa(pessoa, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id){
        logger.info("Delete -> /pessoas/" + id);
        pessoaService.delete(id);
        return ResponseEntity.ok().build();
    }


}
