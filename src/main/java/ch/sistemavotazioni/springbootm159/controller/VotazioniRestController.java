package ch.sistemavotazioni.springbootm159.controller;

import ch.sistemavotazioni.springbootm159.entity.Votazioni;
import ch.sistemavotazioni.springbootm159.service.VotazioniService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class VotazioniRestController {

  final VotazioniService votazioniService;

  public VotazioniRestController(VotazioniService votazioniService) {
    this.votazioniService = votazioniService;
  }

  @GetMapping("/votazioni")
  public List<Votazioni> getAllVotazioni() {
    return votazioniService.getAllVotazioni();
  }
}
