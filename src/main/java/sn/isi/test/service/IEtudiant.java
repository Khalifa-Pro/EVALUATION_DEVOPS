package sn.isi.test.service;

import sn.isi.test.entity.Etudiant;

public interface IEtudiant {
    Etudiant saisir();
    void afficher(Etudiant etudiant);
}
