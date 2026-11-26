import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Groupe {
    private String identifiant;
    private int nombreMaxEtudiants;
    private final Semestre semestre;
    private final List<Etudiant> etudiants = new ArrayList<>();

    public Groupe(String identifiant, int nombreMaxEtudiants, Semestre semestre) {
        if (identifiant == null || identifiant.isBlank()) throw new IllegalArgumentException("Identifiant obligatoire.");
        if (nombreMaxEtudiants <= 0) throw new IllegalArgumentException("La capacité doit être positive.");
        if (semestre == null) throw new IllegalArgumentException("Un groupe doit appartenir à un semestre.");
        this.identifiant = identifiant.trim();
        this.nombreMaxEtudiants = nombreMaxEtudiants;
        this.semestre = semestre;
    }

    public String getIdentifiant() { return identifiant; }
    public int getNombreMaxEtudiants() { return nombreMaxEtudiants; }
    public Semestre getSemestre() { return semestre; }
    public List<Etudiant> getEtudiants() { return Collections.unmodifiableList(etudiants); }

    public void ajouterEtudiant(Etudiant etudiant) {
        if (etudiant == null) throw new IllegalArgumentException("Étudiant obligatoire.");
        if (etudiant.getFiliere() != semestre.getFiliere())
            throw new IllegalArgumentException("L'étudiant doit appartenir à la filière du semestre.");
        if (etudiants.contains(etudiant)) return;
        if (etudiants.size() >= nombreMaxEtudiants)
            throw new IllegalStateException("La capacité maximale du groupe est atteinte.");

        Groupe ancien = etudiant.getGroupesParSemestre().get(semestre.getNumero());
        if (ancien != null && ancien != this) ancien.retirerEtudiantInterne(etudiant);
        etudiants.add(etudiant);
        etudiant.affecterGroupe(semestre.getNumero(), this);
    }

    public boolean supprimerEtudiant(String matricule) {
        for (Etudiant e : new ArrayList<>(etudiants)) {
            if (e.getMatricule().equals(matricule)) {
                retirerEtudiantInterne(e);
                return true;
            }
        }
        return false;
    }

    private void retirerEtudiantInterne(Etudiant e) {
        etudiants.remove(e);
        e.retirerGroupe(semestre.getNumero());
    }

    public void afficherEtudiants() {
        System.out.println("Étudiants du groupe " + identifiant + " :");
        if (etudiants.isEmpty()) System.out.println("Aucun étudiant.");
        for (Etudiant e : etudiants) e.afficherInformations();
    }

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof Groupe g && identifiant.equals(g.identifiant));
    }

    @Override
    public int hashCode() { return identifiant.hashCode(); }
}
