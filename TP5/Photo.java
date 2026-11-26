public class Photo extends Document implements Imprimable {
    private int largeur;
    private int hauteur;

    public Photo(int id, String titre, double tailleMo, int largeur, int hauteur) {
        super(id, titre, tailleMo);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public int getLargeur() { return largeur; }
    public void setLargeur(int largeur) { this.largeur = largeur; }

    public int getHauteur() { return hauteur; }
    public void setHauteur(int hauteur) { this.hauteur = hauteur; }

    @Override
    public void afficherInformations() {
        super.afficherInformations();
        System.out.println("   -> Photo : " + largeur + "x" + hauteur);
    }

    @Override
    public void imprimer() {
        System.out.println("Impression de la photo '" + getTitre() + "' (" + largeur + "x" + hauteur + ")");
    }
}