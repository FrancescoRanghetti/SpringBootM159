package ch.sistemavotazioni.springbootm159.controller;

import ch.sistemavotazioni.springbootm159.entity.Risultati;
import ch.sistemavotazioni.springbootm159.service.RisultatiService;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class RisultatiRestController {

  private RisultatiService risultatiService;

  public RisultatiRestController(final RisultatiService risultatiService) {
    this.risultatiService = risultatiService;
  }

  @PostMapping("/addRisultato")
  public Risultati addRisultato(@RequestBody Risultati risultati) {
    return risultatiService.addRisultato(risultati);
  }

  @PostMapping("/addVoto/{id}/{si}")
  public void addVoto(@PathVariable("id") int id, @PathVariable("si") boolean si) {
    risultatiService.addVoto(id, si);
  }

  @PostMapping("/deleteRisultato")
  public void deleteRisultato(@RequestBody Risultati risultati) {
    risultatiService.deleteisultato(risultati);
  }

  @GetMapping("/getAllRisultati")
  public List<Risultati> getAllRisultati() {
    return risultatiService.getAllRisultati();
  }

  @PostMapping("/addDeleted/{id}/{deleted}")
  public void addDeleted(@PathVariable("id") int id, @PathVariable("deleted") Date deleted) {
    risultatiService.addDeleted(id, deleted);
  }
}
