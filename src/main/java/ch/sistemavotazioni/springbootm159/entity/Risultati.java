package ch.sistemavotazioni.springbootm159.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "risultati")
public class Risultati {

  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "si")
  private Integer si;

  @Column(name = "no")
  private Integer no;

}
