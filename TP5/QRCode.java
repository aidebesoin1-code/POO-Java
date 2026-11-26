public class QRCode implements Imprimable, Partageable {
    private String contenu;

    public QRCode(String contenu) {
        this.contenu = contenu;
    }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    @Override
    public void imprimer() {
        System.out.println("Impression du QRCode : " + contenu);
    }

    @Override
    public void partager() {
        System.out.println("Partage du QRCode : " + contenu);
    }
}