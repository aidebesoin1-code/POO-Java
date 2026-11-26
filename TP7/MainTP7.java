import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainTP7 {
    public static void main(String[] args) {
        GestionnaireReservations gestion = new GestionnaireReservations();

        Chambre c1 = new Chambre(1, "SIMPLE", 300, 1);
        Chambre c2 = new Chambre(2, "DOUBLE", 500, 2);
        Chambre c3 = new Chambre(3, "SUITE", 1200, 4);
        gestion.ajouterChambre(c1);
        gestion.ajouterChambre(c2);
        gestion.ajouterChambre(c3);

        Client ali = new Client(1, "Ali", "ali@mail.com", 30);
        Client sara = new Client(2, "Sara", "sara@mail.com", 17);
        Client bob = new Client(3, "Bob", "bob@mail.com", 40);
        bob.setInterdit(true);

        // ----- Partie 1 : saisie utilisateur (tester : 3, 100, trois) -----
        Scanner scanner = new Scanner(System.in);
        int nombreNuits = 3;
        try {
            System.out.print("Nombre de nuits : ");
            nombreNuits = scanner.nextInt();
            System.out.println("Nombre de nuits : " + nombreNuits);
        } catch (InputMismatchException e) {
            System.out.println("Veuillez saisir un nombre entier.");
            nombreNuits = 3;
        }
        scanner.close();

        // ----- Partie 2 : prix négatif -----
        try {
            new Chambre(10, "SIMPLE", -300, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        // ----- Partie 3/4 : réservations -----
        LocalDate arrivee = LocalDate.of(2026, 12, 10);
        LocalDate depart = LocalDate.of(2026, 12, 15);

        // 1) réservation valide
        essayer(gestion, ali, c2, 5, 2, arrivee, depart);
        // 2) dates inversées
        essayer(gestion, ali, c2, 3, 2, LocalDate.of(2026, 12, 15), LocalDate.of(2026, 12, 12));
        // 3) chevauchement (13 -> 17 décembre)
        essayer(gestion, ali, c2, 4, 2, LocalDate.of(2026, 12, 13), LocalDate.of(2026, 12, 17));
        // 4) capacité dépassée
        essayer(gestion, ali, c2, 5, 3, arrivee, depart);
        // 5) mineur dans une suite
        essayer(gestion, sara, c3, 5, 1, arrivee, depart);
        // 6) client interdit
        essayer(gestion, bob, c1, 5, 1, arrivee, depart);
        // 7) séjour > 14 nuits
        essayer(gestion, ali, c3, 20, 2, LocalDate.of(2027, 1, 1), LocalDate.of(2027, 1, 21));

        // chambre inexistante
        try {
            gestion.chercherChambre(999);
        } catch (ChambreIntrouvableException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        // ----- Transitions -----
        try {
            Reservation r = gestion.chercherReservation(1);
            r.confirmer();
            try {
                r.checkIn(LocalDateTime.of(2026, 12, 10, 9, 0)); // trop tôt
            } catch (OperationReservationInterditeException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
            r.checkIn(LocalDateTime.of(2026, 12, 10, 15, 0));
            System.out.println("Chambre : " + c2);
            double montant = r.checkOut();
            System.out.println("Montant final : " + montant + " DH");

            // annuler une réservation terminée -> exception
            gestion.annulerReservation(1, LocalDateTime.now());
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        // annulation tardive (pénalité, sans exception)
        try {
            Reservation r = gestion.creerReservation(ali, c1, 2, 1,
                    LocalDate.of(2026, 12, 20), LocalDate.of(2026, 12, 22));
            r.confirmer();
            double penalite = gestion.annulerReservation(r.getNumero(), LocalDateTime.of(2026, 12, 19, 10, 0));
            System.out.println("Annulation acceptée, pénalité : " + penalite + " DH");
            gestion.annulerReservation(r.getNumero(), LocalDateTime.now()); // 2e annulation -> exception
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        System.out.println("\n--- Réservations ---");
        gestion.afficherReservations();
    }

    private static void essayer(GestionnaireReservations g, Client client, Chambre chambre,
                                int nuits, int personnes, LocalDate arrivee, LocalDate depart) {
        try {
            Reservation r = g.creerReservation(client, chambre, nuits, personnes, arrivee, depart);
            System.out.println("OK : " + r);
        } catch (Exception e) {
            System.out.println("Refusée : " + e.getClass().getSimpleName() + " -> " + e.getMessage());
        }
    }
}