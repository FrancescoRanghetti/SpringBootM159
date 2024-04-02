package ch.sistemavotazioni.springbootm159.repository;

import ch.sistemavotazioni.springbootm159.entity.Votazioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotazioniRepository extends JpaRepository <Votazioni, Integer> {

}
