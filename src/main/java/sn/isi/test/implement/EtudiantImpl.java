package sn.isi.test.implement;

import sn.isi.test.entity.Etudiant;
import sn.isi.test.service.IEtudiant;

import java.util.Scanner;

public class EtudiantImpl implements IEtudiant {
    @Override
    public Etudiant saisir() {
        Etudiant etudiant = new Etudiant();
        Scanner scan = new Scanner(System.in);

        System.out.print("Entrer votre code étudiant: ");
        String code = scan.nextLine();
        etudiant.setCode(code);

        System.out.print("Entrer votre nom étudiant: ");
        String nom = scan.nextLine();
        etudiant.setNom(nom);

        System.out.print("Entrer votre prenom étudiant: ");
        String prenom = scan.nextLine();
        etudiant.setPrenom(prenom);

        return etudiant;
    }

    @Override
    public void afficher(Etudiant etudiant) {
        System.out.println("------------------------------------------------");
        System.out.println("Code étudiant: "+etudiant.getCode());
        System.out.println("Nom: "+etudiant.getNom());
        System.out.println("Prénom :"+etudiant.getPrenom());
        System.out.println("-------------------------------------------------");
    }
}
