package ch.sistemavotazioni.springbootm159.service;

import ch.sistemavotazioni.springbootm159.entity.Votazioni;
import ch.sistemavotazioni.springbootm159.repository.VotazioniRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VotazioniService {

  final VotazioniRepository votazioniRepository;

  public VotazioniService(VotazioniRepository votazioniRepository) {
    this.votazioniRepository = votazioniRepository;
  }

  public List<Votazioni> getAllVotazioni() {
    return votazioniRepository.findAll();
  }

}
