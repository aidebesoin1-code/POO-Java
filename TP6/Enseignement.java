public class Enseignement {
    private final Enseignant enseignant;
    private final Module module;
    private final int nombreHeures;
    private final Groupe groupe;

    public Enseignement(Enseignant enseignant, Module module, int nombreHeures, Groupe groupe) {
        if (enseignant == null || module == null || groupe == null)
            throw new IllegalArgumentException("Enseignant, module et groupe sont obligatoires.");
        if (nombreHeures <= 0)
            throw new IllegalArgumentException("Le nombre d'heures doit être positif.");
        this.enseignant = enseignant;
        this.module = module;
        this.nombreHeures = nombreHeures;
        this.groupe = groupe;
    }

    public Enseignant getEnseignant() { return enseignant; }
    public Module getModule() { return module; }
    public int getNombreHeures() { return nombreHeures; }
    public Groupe getGroupe() { return groupe; }
}
