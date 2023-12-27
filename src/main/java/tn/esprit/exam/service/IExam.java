package tn.esprit.exam.service;

import tn.esprit.exam.entity.*;
import java.util.Date;
import java.util.List;

public interface IExam {

    public Utilisateur ajouterUtilisateur(Utilisateur u);

    public Programme ajouterProgrammeEtChaine (Programme p);

    public Programme ajouterProgrammeEtAffecterChaine (Programme p, Long chId);

    public void affecterProgrammeAUtilisateur (String prNom, String usrName);

    public List<Utilisateur> recupererUtilisateurs(Proffession p, Date d, Thematique t);

    public void ordonnerChaines();

    public void desaffecterProgrammeDeUtilisateur (String prNom, String usrNom);


}
