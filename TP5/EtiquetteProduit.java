public class EtiquetteProduit implements Imprimable {
    private String reference;
    private String texte;

    public EtiquetteProduit(String reference, String texte) {
        this.reference = reference;
        this.texte = texte;
    }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public String getTexte() { return texte; }
    public void setTexte(String texte) { this.texte = texte; }

    @Override
    public void imprimer() {
        System.out.println("Impression de l'etiquette [" + reference + "] : " + texte);
    }
}