package br.com.fiap.gs.controller;

import br.com.fiap.gs.dto.CadastrarOrganizacaoParceira;
import br.com.fiap.gs.entidade.OrganizacaoParceira;
import br.com.fiap.gs.exception.IniciativaNotFoundException;
import br.com.fiap.gs.repository.OrganizacaoParceiraRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/api/organizacoes")
public class OrganizacaoParceiraController {

    @Autowired
    private OrganizacaoParceiraRepository organizacaoParceiraRepository;

    // Listar todas as organizações
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<OrganizacaoParceira>>> getAllOrganizacaoParceira(
            Pageable pageable, PagedResourcesAssembler<OrganizacaoParceira> assembler) {
        Page<OrganizacaoParceira> organizacaoparceira;
        organizacaoparceira = organizacaoParceiraRepository.findAll(pageable);
        return ResponseEntity.ok(assembler.toModel(organizacaoparceira));
    }

    // Listar por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<OrganizacaoParceira>> getOrganizacaoParceiraById(@PathVariable Long id) {
        return organizacaoParceiraRepository.findById(id)
                .map(organizacaoParceira -> ResponseEntity.ok(EntityModel.of(organizacaoParceira)))
                .orElse(ResponseEntity.notFound().build());
    }

    //
    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid CadastrarOrganizacaoParceira dados,
                                          UriComponentsBuilder uriBuilder) {
        OrganizacaoParceira organizacaoParceira = new OrganizacaoParceira(dados);
        organizacaoParceiraRepository.save(organizacaoParceira);
        var uri = uriBuilder.path("/organizacoes/{id}").buildAndExpand(organizacaoParceira.getId()).toUri();
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

    // Fazer Atualização Organização
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> atualizar(@RequestBody @Valid OrganizacaoParceira dados) {
        OrganizacaoParceira organizacao = (OrganizacaoParceira) organizacaoParceiraRepository.findById(dados.id())
                .orElseThrow(() -> new RuntimeException("Iniciativa não localizada"));
        organizacao.atualizarIniciativa(dados);
        return ResponseEntity.ok().build();
    }

    // Excluir uma Organização
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            organizacaoParceiraRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IniciativaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
