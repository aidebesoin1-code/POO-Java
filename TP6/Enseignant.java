import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Enseignant {
    private String matricule;
    private String nom;
    private String specialite;
    private final List<Enseignement> enseignements = new ArrayList<>();

    public Enseignant(String matricule, String nom, String specialite) {
        setMatricule(matricule);
        setNom(nom);
        setSpecialite(specialite);
    }

    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) {
        if (matricule == null || matricule.isBlank()) throw new IllegalArgumentException("Matricule obligatoire.");
        this.matricule = matricule.trim();
    }

    public String getNom() { return nom; }
    public void setNom(String nom) {
        if (nom == null || nom.isBlank()) throw new IllegalArgumentException("Nom obligatoire.");
        this.nom = nom.trim();
    }

    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) {
        if (specialite == null || specialite.isBlank()) throw new IllegalArgumentException("Spécialité obligatoire.");
        this.specialite = specialite.trim();
    }

    public List<Enseignement> getEnseignements() {
        return Collections.unmodifiableList(enseignements);
    }

    public void ajouterEnseignement(Module module, int heures, Groupe groupe) {
        if (module == null || groupe == null) throw new IllegalArgumentException("Module et groupe obligatoires.");
        if (groupe.getSemestre() == null) throw new IllegalArgumentException("Le groupe doit être rattaché à un semestre.");
        for (Enseignement e : enseignements) {
            if (e.getModule().equals(module) && e.getGroupe().equals(groupe))
                throw new IllegalArgumentException("Cet enseignant assure déjà ce module pour ce groupe.");
        }
        Enseignement e = new Enseignement(this, module, heures, groupe);
        enseignements.add(e);
        module.ajouterEnseignementInterne(e);
    }

    public boolean supprimerEnseignement(String codeModule, String idGroupe) {
        for (Enseignement e : new ArrayList<>(enseignements)) {
            if (e.getModule().getCode().equals(codeModule)
                    && e.getGroupe().getIdentifiant().equals(idGroupe)) {
                enseignements.remove(e);
                e.getModule().retirerEnseignementInterne(e);
                return true;
            }
        }
        return false;
    }

    public int calculerNombreHeures() {
        int total = 0;
        for (Enseignement e : enseignements) total += e.getNombreHeures();
        return total;
    }

    public void afficherModules() {
        System.out.println("Modules assurés par " + nom + " :");
        if (enseignements.isEmpty()) System.out.println("Aucun module.");
        for (Enseignement e : enseignements) {
            System.out.println("- " + e.getModule().getNom()
                    + " | Groupe : " + e.getGroupe().getIdentifiant()
                    + " | " + e.getNombreHeures() + " heures");
        }
    }
}
