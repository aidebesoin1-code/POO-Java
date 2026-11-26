import java.util.ArrayList;
import java.util.List;

public class Universite {
    private final List<Filiere> filieres = new ArrayList<>();
    private final List<Etudiant> etudiants = new ArrayList<>();

    public void ajouterFiliere(Filiere filiere) {
        if (filiere == null) throw new IllegalArgumentException("Filière obligatoire.");
        for (Filiere f : filieres)
            if (f.getCode().equals(filiere.getCode()))
                throw new IllegalArgumentException("Code de filière déjà existant.");
        filieres.add(filiere);
    }

    // Ce point central garantit l'unicité du matricule dans toute l'université.
    public void inscrireEtudiant(Etudiant etudiant, Filiere filiere) {
        if (!filieres.contains(filiere)) throw new IllegalArgumentException("Filière inconnue de l'université.");
        for (Etudiant e : etudiants)
            if (e.getMatricule().equals(etudiant.getMatricule()) && e != etudiant)
                throw new IllegalArgumentException("Matricule déjà utilisé dans l'université.");
        filiere.ajouterEtudiant(etudiant);
        if (!etudiants.contains(etudiant)) etudiants.add(etudiant);
    }

    public List<Filiere> getFilieres() { return List.copyOf(filieres); }
}
