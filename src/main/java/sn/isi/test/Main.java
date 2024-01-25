package sn.isi.test;

import sn.isi.test.implement.EtudiantImpl;
import sn.isi.test.service.IEtudiant;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        IEtudiant iEtudiant = new EtudiantImpl();
        iEtudiant.afficher(iEtudiant.saisir());
    }
}