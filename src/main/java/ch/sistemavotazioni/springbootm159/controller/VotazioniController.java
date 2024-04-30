package ch.sistemavotazioni.springbootm159.controller;

import ch.sistemavotazioni.springbootm159.entity.Votazioni;
import ch.sistemavotazioni.springbootm159.service.VotazioniService;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class VotazioniController {

  final VotazioniService votazioniService;

  public VotazioniController(VotazioniService votazioniService) {
    this.votazioniService = votazioniService;
  }

  @GetMapping("/votazioni")
  public List<Votazioni> getAllVotazioni() {
    return votazioniService.getAllVotazioni();
  }

  @GetMapping("/votazioneById/{id}")
  public Optional<Votazioni> getVotazioneById(@PathVariable("id") int id) {
    return votazioniService.getVotazioneById(id);
  }

  @GetMapping("/votazioni-delete-now/{id}")
  public void deleteNow(@PathVariable("id") int id) {
    votazioniService.deleteNow(id);
  }
}
