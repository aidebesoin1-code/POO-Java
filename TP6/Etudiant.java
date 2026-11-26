import java.util.HashMap;
import java.util.Map;

public class Etudiant {
    private String matricule;
    private String nom;
    private String prenom;
    private String email;
    private Filiere filiere;
    // Un étudiant peut être dans un groupe différent selon le semestre.
    private final Map<Integer, Groupe> groupesParSemestre = new HashMap<>();

    public Etudiant(String matricule, String nom, String prenom, String email) {
        setMatricule(matricule);
        setNom(nom);
        setPrenom(prenom);
        setEmail(email);
    }

    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) {
        if (matricule == null || matricule.isBlank())
            throw new IllegalArgumentException("Le matricule est obligatoire.");
        this.matricule = matricule.trim();
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = valeurObligatoire(nom, "nom"); }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = valeurObligatoire(prenom, "prénom"); }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = valeurObligatoire(email, "email"); }

    public Filiere getFiliere() { return filiere; }

    // Utilisé par Filiere pour maintenir la relation bidirectionnelle.
    void setFiliere(Filiere filiere) { this.filiere = filiere; }

    public Map<Integer, Groupe> getGroupesParSemestre() {
        return Map.copyOf(groupesParSemestre);
    }

    void affecterGroupe(int numeroSemestre, Groupe groupe) {
        groupesParSemestre.put(numeroSemestre, groupe);
    }

    void retirerGroupe(int numeroSemestre) {
        groupesParSemestre.remove(numeroSemestre);
    }

    public void afficherInformations() {
        System.out.println("Matricule : " + matricule
                + " | Nom : " + nom
                + " | Prénom : " + prenom
                + " | Email : " + email
                + " | Filière : " + (filiere == null ? "Non affecté" : filiere.getNom()));
    }

    private static String valeurObligatoire(String valeur, String champ) {
        if (valeur == null || valeur.isBlank())
            throw new IllegalArgumentException("Le " + champ + " est obligatoire.");
        return valeur.trim();
    }

    @Override
    public boolean equals(Object objet) {
        if (this == objet) return true;
        if (!(objet instanceof Etudiant autre)) return false;
        return matricule.equals(autre.matricule);
    }

    @Override
    public int hashCode() { return matricule.hashCode(); }
}
