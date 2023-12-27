package tn.esprit.exam.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "programme")

public class Programme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long prId;

    private String prNom;

    // many to many (user)
    @ManyToMany(mappedBy="programmes", cascade = CascadeType.ALL)
    private Set<Utilisateur> utilisateurs;

    // many to one (chaine)
    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    Chaine chaine;
}
