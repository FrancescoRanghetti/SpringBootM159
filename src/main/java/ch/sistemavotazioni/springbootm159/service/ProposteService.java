package ch.sistemavotazioni.springbootm159.service;

import ch.sistemavotazioni.springbootm159.entity.Proposta;
import ch.sistemavotazioni.springbootm159.entity.Votazioni;
import ch.sistemavotazioni.springbootm159.repository.ProposteRepository;
import ch.sistemavotazioni.springbootm159.repository.VotazioniRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProposteService {

  final ProposteRepository proposteRepository;

  final VotazioniRepository votazioniRepository;

  public ProposteService(ProposteRepository proposteRepository, VotazioniRepository votazioniRepository) {
    this.proposteRepository = proposteRepository;
    this.votazioniRepository = votazioniRepository;
  }

  public Proposta saveProposte(Proposta proposta) {
    return proposteRepository.save(proposta);
  }

  public Votazioni saveProposteAdmin(Votazioni votazione) {
    return votazioniRepository.save(votazione);
  }

  public List<Proposta> getAll() {
    return proposteRepository.findAll();
  }

  public void deteleProposta(Proposta proposta) {
    System.out.println("entra qua");
    proposteRepository.delete(proposta);
  }
}
