package br.com.fiap.gs.service;

import br.com.fiap.gs.dto.CadastrarIniciativa;
import br.com.fiap.gs.entidade.Iniciativa;
import br.com.fiap.gs.repository.IniciativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IniciativaService {

    @Autowired
    private IniciativaRepository iniciativaRepository;
    public Iniciativa criarIniciativa(CadastrarIniciativa cadastrarIniciativa) {
        Iniciativa iniciativa = new Iniciativa(cadastrarIniciativa);
        return iniciativaRepository.save(iniciativa);
    }
}
