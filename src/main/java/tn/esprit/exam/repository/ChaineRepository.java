package tn.esprit.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.exam.entity.Chaine;

import java.util.List;

@Repository
public interface ChaineRepository extends JpaRepository<Chaine, Long> {

    @Query("SELECT p.chaine, count(p) AS cp FROM Programme p " +
            "INNER JOIN p.utilisateurs u " +
            "WHERE u.usrId IS NOT NULL " +
            "GROUP BY p.chaine ORDER BY cp DESC")
    List<Object[]> listerchaines();
}
