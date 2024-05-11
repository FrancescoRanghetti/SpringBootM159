package ch.sistemavotazioni.springbootm159.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "risultati")
public class Risultati {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id", nullable = false) private Integer id;

  @Column(name = "nome", nullable = false, length = 500) private String nome;

  @Column(name = "descrizione", nullable = false, length = 500) private String descrizione;

  @Column(name = "autore", nullable = false, length = 100) private String autore;

  @Column(name = "si") private Integer si;

  @Column(name = "no") private Integer no;

  @Column(name = "deleted") private LocalDate deleted;
}
