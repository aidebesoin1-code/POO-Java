import java.util.ArrayList;
import java.util.List;

public class TestDocuments {

    public static void imprimerTout(List<Imprimable> elements) {
        for (Imprimable element : elements) {
            element.imprimer();
        }
    }

    public static void main(String[] args) {
        Rapport rapport = new Rapport(1, "Bilan 2025", 2.5, 40);
        Photo photo = new Photo(2, "Logo", 1.2, 1920, 1080);
        Video video = new Video(3, "Presentation", 250.0, 12.5, "1080p");
        Facture facture = new Facture(4, "Facture client", 0.3, "F-2026-001", 1500.0);
        Audio audio = new Audio(5, "Podcast", 30.0, 45.0);

        // Liste de documents
        List<Document> documents = new ArrayList<>();
        documents.add(rapport);
        documents.add(photo);
        documents.add(video);
        documents.add(facture);
        documents.add(audio);

        System.out.println("=== Informations des documents ===");
        for (Document d : documents) {
            d.afficherInformations();
        }

        // Liste d'imprimables (documents + non-documents)
        List<Imprimable> aImprimer = new ArrayList<>();
        aImprimer.add(rapport);
        aImprimer.add(photo);
        aImprimer.add(facture);
        aImprimer.add(new EtiquetteProduit("REF-001", "Produit fragile"));
        aImprimer.add(new QRCode("https://exemple.com"));

        System.out.println("\n=== Impression ===");
        imprimerTout(aImprimer);
    }
}