package tn.esprit.exam.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.exam.entity.*;
import tn.esprit.exam.repository.ChaineRepository;
import tn.esprit.exam.repository.ProgrammeRepository;
import tn.esprit.exam.repository.UtilisateurRepository;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class ExamService implements IExam{

    ProgrammeRepository programmeRepository;
    ChaineRepository chaineRepository;
    UtilisateurRepository utilisateurRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Utilisateur ajouterUtilisateur(Utilisateur u) {
        return utilisateurRepository.save(u);
    }

    @Override
    public Programme ajouterProgrammeEtChaine(Programme p) {
        return programmeRepository.save(p);
    }

    @Override
    public Programme ajouterProgrammeEtAffecterChaine(Programme p, Long chId) {
        Chaine ch = chaineRepository.findById(chId).get();

        p.setChaine(ch);
        return programmeRepository.save(p);
    }

    @Override
    public void affecterProgrammeAUtilisateur(String prNom, String usrName) {
        Programme p = programmeRepository.findByPrNom(prNom);
        Utilisateur utilisateur = utilisateurRepository.findByUsrName(usrName);

        utilisateur.getProgrammes().add(p);

        utilisateurRepository.save(utilisateur);
    }

    @Override
    public List<Utilisateur> recupererUtilisateurs(Proffession p, Date d, Thematique t) {
        String jpql = "SELECT DISTINCT u FROM Utilisateur u " +
                "JOIN u.programmes p " +
                "JOIN p.chaine c " +
                "WHERE u.proffession = :p " +
                "AND u.usrDateInscription > :d " +
                "AND c.thematique = :t";

        return entityManager.createQuery(jpql, Utilisateur.class)
                .setParameter("p", p)
                .setParameter("d", d)
                .setParameter("t", t)
                .getResultList();
    }

    @Override
    @Scheduled(fixedDelay = 20000)
    public void ordonnerChaines() {

        List<Object[]> l = chaineRepository.listerchaines();

        for (Object[] obj : l) {
            Chaine chaine = (Chaine) obj[0];
            Long nbre = (Long) obj[1];
            log.info("Chaine : " + chaine.getChNom() + ".  Nombre de fois où les programmes de cette Chaine sont marqués comme favoris  : " + nbre);
        }
    }


    @Override
    public void desaffecterProgrammeDeUtilisateur(String prNom, String usrName) {
        Programme prog = programmeRepository.findByPrNom(prNom);
        Utilisateur user = utilisateurRepository.findByUsrName(usrName);
        // on enlève le fils du parent :
        user.getProgrammes().remove(prog);
        utilisateurRepository.save(user);
    }


}
