package tn.esprit.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.exam.entity.Programme;

@Repository
public interface ProgrammeRepository extends JpaRepository<Programme, Long> {
    Programme findByPrNom(String prNom);
}
