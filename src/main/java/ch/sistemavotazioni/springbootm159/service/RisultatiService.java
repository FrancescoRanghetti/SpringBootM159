package ch.sistemavotazioni.springbootm159.service;

import ch.sistemavotazioni.springbootm159.entity.Risultati;
import ch.sistemavotazioni.springbootm159.repository.RisultatiRepository;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RisultatiService {

  final RisultatiRepository risultatiRepository;

  public RisultatiService(final RisultatiRepository risultatiRepository) {
    this.risultatiRepository = risultatiRepository;
  }

  public Risultati addRisultato(Risultati risultati) {
    return risultatiRepository.save(risultati);
  }

  public void deleteisultato(Risultati risultati) {
    risultatiRepository.delete(risultati);
  }

  public List<Risultati> getAllRisultati() {
    return risultatiRepository.findAll();
  }

  @Transactional
  public void addVoto(Integer id, boolean si) {
    int voto = 0;
    Risultati risultato = risultatiRepository.getReferenceById(id);
    if (si) {
      voto = risultato.getSi() + 1;
      risultatiRepository.updateVotoSi(voto, id);
    } else {
      voto = risultato.getNo() + 1;
      risultatiRepository.updateVotoNo(voto, id);
    }
  }

  @Transactional
  public void addDeleted(Integer id, Date deleted) {
    risultatiRepository.updateDateDeleted(deleted, id);
  }
}
