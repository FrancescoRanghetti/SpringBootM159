package ch.sistemavotazioni.springbootm159.repository;

import ch.sistemavotazioni.springbootm159.entity.Risultati;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RisultatiRepository extends JpaRepository<Risultati, Integer> {

  @Modifying
  @Query(value = "UPDATE risultati SET si = :si  WHERE id = :id", nativeQuery = true)
  void updateVotoSi(@Param("si") int si, @Param("id") Integer id);

  @Modifying
  @Query(value = "UPDATE risultati SET no = :no  WHERE id = :id", nativeQuery = true)
  void updateVotoNo(@Param("no") int no, @Param("id") Integer id);

  @Modifying
  @Query(value = "UPDATE risultati SET deleted = :deleted  WHERE id = :id", nativeQuery = true)
  void updateDateDeleted(@Param("deleted") Date deleted, @Param("id") Integer id);
}
