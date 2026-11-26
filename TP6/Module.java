import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Module {
    private String code;
    private String nom;
    private int volumeHoraire;
    // Les enseignements sont des associations : un module peut être partagé.
    private final List<Enseignement> enseignements = new ArrayList<>();

    public Module(String code, String nom, int volumeHoraire) {
        setCode(code);
        setNom(nom);
        setVolumeHoraire(volumeHoraire);
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

    public int getVolumeHoraire() { return volumeHoraire; }
    public void setVolumeHoraire(int volumeHoraire) {
        if (volumeHoraire <= 0) throw new IllegalArgumentException("Le volume horaire doit être positif.");
        this.volumeHoraire = volumeHoraire;
    }

    public List<Enseignement> getEnseignements() {
        return Collections.unmodifiableList(enseignements);
    }

    void ajouterEnseignementInterne(Enseignement e) {
        if (!enseignements.contains(e)) enseignements.add(e);
    }

    void retirerEnseignementInterne(Enseignement e) { enseignements.remove(e); }

    public void afficherEnseignants() {
        System.out.println("Enseignants du module " + nom + " :");
        if (enseignements.isEmpty()) System.out.println("Aucun enseignant.");
        for (Enseignement e : enseignements) {
            System.out.println("- " + e.getEnseignant().getNom()
                    + " | Groupe : " + e.getGroupe().getIdentifiant()
                    + " | Heures : " + e.getNombreHeures());
        }
    }

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof Module m && code.equals(m.code));
    }

    @Override
    public int hashCode() { return code.hashCode(); }
}
