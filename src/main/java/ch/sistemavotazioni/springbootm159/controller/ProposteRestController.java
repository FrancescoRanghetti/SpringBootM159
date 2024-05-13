package ch.sistemavotazioni.springbootm159.controller;

import ch.sistemavotazioni.springbootm159.entity.Proposta;
import ch.sistemavotazioni.springbootm159.entity.Votazioni;
import ch.sistemavotazioni.springbootm159.service.ProposteService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class ProposteRestController {

  final ProposteService proposteService;

  public ProposteRestController(ProposteService proposteService) {
    this.proposteService = proposteService;
  }

  @PostMapping("/addPropose")
  public Proposta addPropose(@RequestBody Proposta proposta) {
    return proposteService.saveProposte(proposta);
  }

  @PostMapping("/addProposeAdmin")
  public Votazioni addProposeAdmin(@RequestBody Votazioni votazione) {
    return proposteService.saveProposteAdmin(votazione);
  }

  @GetMapping("/getAllPropose")
  public List<Proposta> getAllPropose() {
    return proposteService.getAll();
  }

  @PostMapping("/deletePropose")
  public void deletePropose(@RequestBody Proposta proposta) {
    proposteService.deteleProposta(proposta);
  }
}
