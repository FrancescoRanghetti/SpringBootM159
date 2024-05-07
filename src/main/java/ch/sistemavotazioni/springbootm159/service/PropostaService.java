package ch.sistemavotazioni.springbootm159.service;

import ch.sistemavotazioni.springbootm159.entity.Proposta;
import ch.sistemavotazioni.springbootm159.repository.ProposteRepository;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {
    final ProposteRepository proposteRepository;

    public PropostaService(ProposteRepository proposteRepository) {
        this.proposteRepository = proposteRepository;
    }

    public Proposta saveProposte(Proposta proposta) {
       return proposteRepository.save(proposta);
    }
}
