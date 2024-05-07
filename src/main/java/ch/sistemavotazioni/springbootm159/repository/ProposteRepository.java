package ch.sistemavotazioni.springbootm159.repository;

import ch.sistemavotazioni.springbootm159.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposteRepository extends JpaRepository<Proposta, Integer> {
}
