package ch.sistemavotazioni.springbootm159.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "risultati")
public class Risultati {

  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

//  @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "idVotazione", nullable = false) private Votazioni idVotazione;

  @Column(name = "nome")
  private String nome;

  @Column(name = "si")
  private Integer si;

  @Column(name = "no")
  private Integer no;

}
