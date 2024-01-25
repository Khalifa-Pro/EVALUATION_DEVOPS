package sn.isi.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Data
public class Etudiant {
    private String code;
    private String nom;
    private String prenom;
    private String filiere;
}
