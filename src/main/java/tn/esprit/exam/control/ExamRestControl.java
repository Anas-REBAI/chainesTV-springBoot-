package tn.esprit.exam.control;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.exam.entity.Proffession;
import tn.esprit.exam.entity.Programme;
import tn.esprit.exam.entity.Thematique;
import tn.esprit.exam.entity.Utilisateur;
import tn.esprit.exam.service.IExam;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Tag(name = "Les méthodes de l'examen")

public class ExamRestControl {

    // Interface dans package Service
    IExam iExam;

    // Question 1 : ajouter user (simple add)
    @PostMapping("/add-user")
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur u) {
        Utilisateur user = iExam.ajouterUtilisateur(u);
        return user;
    }

    // Question 2 : add Programme Et Chaine  (cas 1)
    @PostMapping("/ajouter-programme-et-chaine")
    public Programme addProgrammeEtChaine(@RequestBody Programme p) {
        Programme prog = iExam.ajouterProgrammeEtChaine(p);
        return prog;
    }

    // Question 3 : ajout haja mefamech o naffectilha haja mawjouda fl BD
    @PostMapping("/ajouter-program-et-affecter-channel/{ch-id}")
    public Programme ajouterProgrammeEtAffecterChaine(@RequestBody Programme p,
                                                 @PathVariable("ch-id") Long chId) {
        return iExam.ajouterProgrammeEtAffecterChaine(p, chId);
    }

    // Question 5 : affecti haja li haja deja existe dans BD (cas 3)
    @PutMapping("/affecter-program-a-user/{pr-nom}/{usr-name}")
    public void affecterProgrammeAUtilisateur(
            @PathVariable("pr-nom") String prNom,
            @PathVariable("usr-name") String usrName) {
        iExam.affecterProgrammeAUtilisateur(prNom, usrName);
    }

    // Question 6 :
    @GetMapping("/recuperer-utilisateurs")
    public List<Utilisateur> recupererUtilisateursTest(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @RequestParam("profession") Proffession profession,
            @RequestParam("thematique") Thematique thematique) {

        return iExam.recupererUtilisateurs(profession, date, thematique);
    }

    // Question 7 (Scheduler) : Vous n'avez pas besoin d'ajouter de méthode spécifique dans le contrôleur pour cette tâche,
    // car elle est déclenchée automatiquement par le planificateur


    // Question 8 : Desaffectation (cas 6)
    @PutMapping("/Desaffecter-program-a-user/{pr-nom}/{usr-name}")
    public void desaffecterProgrammeAUtilisateur(
            @PathVariable("pr-nom") String prNom,
            @PathVariable("usr-name") String usrName) {
        iExam.desaffecterProgrammeDeUtilisateur(prNom, usrName);
    }



}
