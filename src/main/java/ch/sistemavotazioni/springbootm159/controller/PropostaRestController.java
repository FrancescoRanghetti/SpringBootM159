package ch.sistemavotazioni.springbootm159.controller;


import ch.sistemavotazioni.springbootm159.entity.Proposta;
import ch.sistemavotazioni.springbootm159.service.PropostaService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class PropostaRestController {


    final PropostaService propostaService;
    public PropostaRestController(PropostaService propostaService) {
        this.propostaService = propostaService;
    }

    @PutMapping("/addPropose")
    public Proposta addPropose(@RequestBody Proposta proposta){
       return propostaService.saveProposte(proposta);
    }

}
