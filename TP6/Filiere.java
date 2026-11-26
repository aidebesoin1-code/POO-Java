import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Filiere {
    // La limite n'est pas indiquée dans l'énoncé : valeur modifiable ici.
    public static final int MAX_ETUDIANTS = 500;

    private String code;
    private String nom;
    private final List<Etudiant> etudiants = new ArrayList<>();
    // Composition : les quatre semestres sont créés et gérés par la filière.
    private final List<Semestre> semestres = new ArrayList<>();

    public Filiere(String code, String nom) {
        if (code == null || code.isBlank() || nom == null || nom.isBlank())
            throw new IllegalArgumentException("Code et nom de filière obligatoires.");
        this.code = code.trim();
        this.nom = nom.trim();
        for (int i = 1; i <= 4; i++) semestres.add(new Semestre(i, this));
    }

    public String getCode() { return code; }
    public void setCode(String code) {
        if (code == null || code.isBlank()) throw new IllegalArgumentException("Code obligatoire.");
        this.code = code.trim();
    }
    public String getNom() { return nom; }
    public void setNom(String nom) {
        if (nom == null || nom.isBlank()) throw new IllegalArgumentException("Nom obligatoire.");
        this.nom = nom.trim();
    }
    public List<Etudiant> getEtudiants() { return Collections.unmodifiableList(etudiants); }
    public List<Semestre> getSemestres() { return Collections.unmodifiableList(semestres); }

    public void ajouterEtudiant(Etudiant etudiant) {
        if (etudiant == null) throw new IllegalArgumentException("Étudiant obligatoire.");
        if (rechercherEtudiant(etudiant.getMatricule()) != null)
            throw new IllegalArgumentException("Cet étudiant est déjà dans cette filière.");
        if (etudiants.size() >= MAX_ETUDIANTS)
            throw new IllegalStateException("La capacité maximale de la filière est atteinte.");

        // Maintien de la cohérence bidirectionnelle en cas de transfert.
        Filiere ancienne = etudiant.getFiliere();
        if (ancienne != null && ancienne != this) ancienne.retirerEtudiantInterne(etudiant);
        etudiants.add(etudiant);
        etudiant.setFiliere(this);
    }

    private void retirerEtudiantInterne(Etudiant e) {
        etudiants.remove(e);
        e.setFiliere(null);
    }

    public Etudiant rechercherEtudiant(String matricule) {
        for (Etudiant e : etudiants)
            if (e.getMatricule().equals(matricule)) return e;
        return null;
    }

    public boolean supprimerEtudiant(String matricule) {
        Etudiant e = rechercherEtudiant(matricule);
        if (e == null) return false;
        // Retirer également l'étudiant de ses groupes éventuels.
        for (Groupe g : tousLesGroupes()) g.supprimerEtudiant(matricule);
        retirerEtudiantInterne(e);
        return true;
    }

    public int nombreEtudiants() { return etudiants.size(); }

    public Semestre rechercherSemestre(int numero) {
        for (Semestre s : semestres) if (s.getNumero() == numero) return s;
        return null;
    }

    public void afficherEtudiants() {
        System.out.println("Étudiants de la filière " + nom + " :");
        if (etudiants.isEmpty()) System.out.println("Aucun étudiant.");
        for (Etudiant e : etudiants) e.afficherInformations();
    }

    private List<Groupe> tousLesGroupes() {
        List<Groupe> resultat = new ArrayList<>();
        for (Semestre s : semestres) resultat.addAll(s.getGroupes());
        return resultat;
    }
}
