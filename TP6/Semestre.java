import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Semestre {
    private final int numero;
    private final Filiere filiere;
    private final List<Module> modules = new ArrayList<>();
    private final List<Groupe> groupes = new ArrayList<>();

    public Semestre(int numero, Filiere filiere) {
        if (numero < 1 || numero > 4) throw new IllegalArgumentException("Le numéro du semestre doit être entre 1 et 4.");
        if (filiere == null) throw new IllegalArgumentException("Une filière est obligatoire.");
        this.numero = numero;
        this.filiere = filiere;
    }

    public int getNumero() { return numero; }
    public Filiere getFiliere() { return filiere; }
    public List<Module> getModules() { return Collections.unmodifiableList(modules); }
    public List<Groupe> getGroupes() { return Collections.unmodifiableList(groupes); }

    public void ajouterModule(Module module) {
        if (module == null) throw new IllegalArgumentException("Module obligatoire.");
        if (rechercherModule(module.getCode()) != null)
            throw new IllegalArgumentException("Un module avec ce code existe déjà dans ce semestre.");
        modules.add(module);
    }

    public Module rechercherModule(String code) {
        for (Module m : modules) if (m.getCode().equals(code)) return m;
        return null;
    }

    public boolean supprimerModule(String code) {
        Module m = rechercherModule(code);
        return m != null && modules.remove(m);
    }

    public void afficherModules() {
        System.out.println("Modules du semestre " + numero + " (" + filiere.getNom() + ") :");
        if (modules.isEmpty()) System.out.println("Aucun module.");
        for (Module m : modules)
            System.out.println("- " + m.getCode() + " | " + m.getNom()
                    + " | Volume horaire : " + m.getVolumeHoraire() + " h");
    }

    public void ajouterGroupe(Groupe groupe) {
        if (groupe == null || groupe.getSemestre() != this)
            throw new IllegalArgumentException("Le groupe doit être créé pour ce semestre.");
        for (Groupe g : groupes)
            if (g.getIdentifiant().equals(groupe.getIdentifiant()))
                throw new IllegalArgumentException("Identifiant de groupe déjà utilisé dans ce semestre.");
        groupes.add(groupe);
    }

    public Groupe rechercherGroupe(String identifiant) {
        for (Groupe g : groupes) if (g.getIdentifiant().equals(identifiant)) return g;
        return null;
    }

    public boolean supprimerGroupe(String identifiant) {
        Groupe g = rechercherGroupe(identifiant);
        if (g == null) return false;
        if (!g.getEtudiants().isEmpty())
            throw new IllegalStateException("Impossible de supprimer un groupe non vide.");
        return groupes.remove(g);
    }
}
