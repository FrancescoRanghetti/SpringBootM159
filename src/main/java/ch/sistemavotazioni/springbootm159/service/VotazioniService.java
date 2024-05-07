package ch.sistemavotazioni.springbootm159.service;

import ch.sistemavotazioni.springbootm159.entity.Votazioni;
import ch.sistemavotazioni.springbootm159.repository.VotazioniRepository;
import java.util.List;
import java.util.Optional;
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

  public Optional<Votazioni> getVotazioneById(int id) {
    return votazioniRepository.findById(id);
  }

  public void deleteNow(int id) {
    votazioniRepository.deleteById(id);
  }

}
