package br.com.fiap.gs.controller;

import br.com.fiap.gs.dto.CadastrarIniciativa;
import br.com.fiap.gs.entidade.Iniciativa;
import br.com.fiap.gs.exception.IniciativaNotFoundException;
import br.com.fiap.gs.repository.IniciativaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/iniciativas")
public class IniciativaController {

    @Autowired
    private IniciativaRepository iniciativaRepository;

    // Listar todas as iniciativas
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Iniciativa>>> getAllIniciativas(
            Pageable pageable, PagedResourcesAssembler<Iniciativa> assembler) {
        Page<Iniciativa> iniciativas = iniciativaRepository.findAll(pageable);
        return ResponseEntity.ok(assembler.toModel(iniciativas));
    }

    // Retornar iniciativas do banco por ID
    @GetMapping("/{id}")
    public ResponseEntity<Iniciativa> getIniciativaById(@PathVariable Long id) {
        try {
            Iniciativa iniciativa = iniciativaRepository.getIniciativaById(id);
            return ResponseEntity.ok(iniciativa);
        } catch (IniciativaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Realizar Cadastro Iniciativa
    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid CadastrarIniciativa dados,
                                          UriComponentsBuilder uriBuilder) {
        Iniciativa iniciativa = new Iniciativa(dados);
        iniciativaRepository.save(iniciativa);
        var uri = uriBuilder.path("/iniciativas/{id}").buildAndExpand(iniciativa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n")
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
    }

    // Fazer Atualização Iniciativa
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> atualizar(@RequestBody @Valid Iniciativa dados) {
        Iniciativa iniciativa = iniciativaRepository.findById(dados.id())
                .orElseThrow(() -> new RuntimeException("Iniciativa não localizada"));
        iniciativa.atualizarIniciativa(dados);
        return ResponseEntity.ok().build();
    }

    // Deletar uma iniciativa
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            iniciativaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IniciativaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
