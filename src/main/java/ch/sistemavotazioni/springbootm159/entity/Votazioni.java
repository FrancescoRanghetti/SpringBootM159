package ch.sistemavotazioni.springbootm159.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "votazioni")
public class Votazioni {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "nome", length = 500)
  private String nome;

  @Column(name = "descrizione", length = 500)
  private String descrizione;

  @Column(name = "autore", length = 100)
  private String autore;
}
