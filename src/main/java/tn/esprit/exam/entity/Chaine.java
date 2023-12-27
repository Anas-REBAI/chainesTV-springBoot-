package tn.esprit.exam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chaine")
public class Chaine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chId;

    private String chNom;

    @Enumerated(EnumType.STRING)
    private Thematique thematique;
}
