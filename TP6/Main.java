public class Main {
    public static void main(String[] args) {
        // PARTIE 1 : création et manipulation d'une ArrayList d'étudiants.
        Etudiant e1 = new Etudiant("E001", "Diallo", "Sara", "sara@email.com");
        Etudiant e2 = new Etudiant("E002", "Ndiaye", "Moussa", "moussa@email.com");
        Etudiant e3 = new Etudiant("E003", "Fall", "Awa", "awa@email.com");
        Etudiant e4 = new Etudiant("E004", "Diop", "Ibrahima", "ibrahima@email.com");

        java.util.ArrayList<Etudiant> liste = new java.util.ArrayList<>();
        liste.add(e1); liste.add(e2); liste.add(e3); liste.add(e4);
        System.out.println("=== PARTIE 1 : LISTE DES ÉTUDIANTS ===");
        for (Etudiant e : liste) e.afficherInformations();
        System.out.println("Nombre : " + liste.size());
        System.out.println("Étudiant à l'indice 0 : " + liste.get(0).getPrenom());
        // Exemples des opérations demandées : add(), get(), size(), remove().
        liste.remove(e4);

        // PARTIE 2 : agrégation Filiere - Etudiant.
        System.out.println("\n=== PARTIE 2 : FILIÈRE ===");
        Universite universite = new Universite();
        Filiere gi = new Filiere("GI", "Génie Informatique");
        Filiere cyber = new Filiere("CY", "Cybersécurité");
        universite.ajouterFiliere(gi);
        universite.ajouterFiliere(cyber);
        universite.inscrireEtudiant(e1, gi);
        universite.inscrireEtudiant(e2, gi);
        universite.inscrireEtudiant(e3, gi);
        gi.afficherEtudiants();
        System.out.println("Recherche E002 : " + gi.rechercherEtudiant("E002").getNom());
        System.out.println("Nombre d'étudiants : " + gi.nombreEtudiants());
        gi.supprimerEtudiant("E003");

        // PARTIE 2' : composition Filiere - Semestre et agrégation Semestre - Module.
        System.out.println("\n=== PARTIE 2' : SEMESTRES ET MODULES ===");
        Semestre s1 = gi.rechercherSemestre(1);
        Module java = new Module("M101", "Programmation Java", 40);
        Module anglais = new Module("M102", "Anglais", 20);
        s1.ajouterModule(java);
        s1.ajouterModule(anglais);
        s1.afficherModules();

        // Le même module Anglais peut être utilisé dans une autre filière.
        cyber.rechercherSemestre(1).ajouterModule(anglais);

        // PARTIE 3 : relation bidirectionnelle Etudiant - Filiere.
        System.out.println("\n=== PARTIE 3 : COHÉRENCE DES RELATIONS ===");
        System.out.println("Filière de Sara : " + e1.getFiliere().getNom());
        // Le transfert met automatiquement à jour les deux côtés.
        universite.inscrireEtudiant(e1, cyber);
        System.out.println("Après transfert, filière de Sara : " + e1.getFiliere().getNom());
        System.out.println("Effectif GI : " + gi.nombreEtudiants());
        System.out.println("Effectif Cyber : " + cyber.nombreEtudiants());

        // PARTIE 4 et extension : groupes, enseignants et enseignements.
        System.out.println("\n=== PARTIE 4 : GROUPES ET ENSEIGNANTS ===");
        Semestre cyberS1 = cyber.rechercherSemestre(1);
        cyberS1.ajouterModule(java); // Le module est partagé entre les deux filières.
        Groupe groupeA = new Groupe("CY-S1-A", 30, cyberS1);
        cyberS1.ajouterGroupe(groupeA);
        groupeA.ajouterEtudiant(e1);
        groupeA.afficherEtudiants();

        Enseignant prof = new Enseignant("P001", "M. Ba", "Informatique");
        prof.ajouterEnseignement(java, 18, groupeA);
        prof.ajouterEnseignement(anglais, 10, groupeA);

        prof.afficherModules();
        java.afficherEnseignants();
        System.out.println("Total d'heures assurées par " + prof.getNom()
                + " : " + prof.calculerNombreHeures() + " h");

        // Tests de règles métier.
        System.out.println("\n=== TESTS DES CONTRAINTES ===");
        try {
            cyberS1.ajouterModule(new Module("M101", "Autre Java", 30));
        } catch (IllegalArgumentException ex) {
            System.out.println("Doublon module détecté : " + ex.getMessage());
        }

        try {
            groupeA.ajouterEtudiant(e1);
            System.out.println("Un même étudiant n'est pas ajouté deux fois au groupe.");
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("\nFin des tests.");
    }
}
