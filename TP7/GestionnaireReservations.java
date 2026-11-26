import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestionnaireReservations {
    private static final int MAX_RESERVATIONS_ACTIVES = 3;
    private static final int SEUIL_VALIDATION_NUITS = 14;

    private List<Chambre> chambres = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
    private int prochainNumero = 1;

    public void ajouterChambre(Chambre c) {
        chambres.add(c);
    }

    public Chambre chercherChambre(int numero) throws ChambreIntrouvableException {
        for (Chambre c : chambres) {
            if (c.getNumero() == numero) {
                return c;
            }
        }
        throw new ChambreIntrouvableException("Chambre numéro " + numero + " inexistante.");
    }

    public Reservation chercherReservation(int numero) throws ReservationIntrouvableException {
        for (Reservation r : reservations) {
            if (r.getNumero() == numero) {
                return r;
            }
        }
        throw new ReservationIntrouvableException("Réservation numéro " + numero + " introuvable.");
    }

    public Reservation creerReservation(Client client, Chambre chambre, Integer nombreNuits,
                                        int nombrePersonnes, LocalDate arrivee, LocalDate depart)
            throws Exception {

        // --- Contrôles de base ---
        if (client == null) {
            throw new IllegalArgumentException("Le client est obligatoire.");
        }
        if (chambre == null) {
            throw new ChambreIntrouvableException("Chambre inexistante.");
        }
        if (nombreNuits == null || nombreNuits <= 0) {
            throw new IllegalArgumentException("Le nombre de nuits doit être strictement positif.");
        }
        if (nombrePersonnes <= 0) {
            throw new IllegalArgumentException("Le nombre de personnes doit être strictement positif.");
        }

        // --- Règles liées au client ---
        if (client.isInterdit()) {
            throw new ClientInterditException("Le client " + client.getNom() + " est interdit de séjour.");
        }
        if (nombreActives(client) >= MAX_RESERVATIONS_ACTIVES) {
            throw new LimiteReservationsAtteinteException(
                    "Le client a déjà " + MAX_RESERVATIONS_ACTIVES + " réservations actives.");
        }
        if (chambre.isSuite() && !client.isMajeur()) {
            throw new ClientMineurException("Une suite ne peut accueillir que des clients majeurs.");
        }

        // --- Règles liées à la chambre ---
        if (nombrePersonnes > chambre.getCapacite()) {
            throw new CapaciteChambreDepasseeException(
                    "La chambre " + chambre.getNumero() + " accepte " + chambre.getCapacite()
                            + " personne(s) maximum.");
        }

        // --- Dates (le constructeur de Reservation vérifie depart > arrivee) ---
        Reservation r = new Reservation(prochainNumero, client, chambre, nombrePersonnes, arrivee, depart);
        if (r.getNombreNuits() != nombreNuits) {
            throw new DatesReservationInvalidesException(
                    "Le nombre de nuits (" + nombreNuits + ") ne correspond pas aux dates ("
                            + r.getNombreNuits() + " nuits).");
        }

        // --- Chevauchement ---
        for (Reservation autre : reservations) {
            if (autre.getChambre() == chambre && autre.bloqueLaChambre() && autre.chevauche(arrivee, depart)) {
                throw new ChambreIndisponibleException(
                        "La chambre " + chambre.getNumero() + " est déjà réservée du "
                                + autre.getDateArrivee() + " au " + autre.getDateDepart() + ".");
            }
        }

        // --- Séjour long : validation du responsable ---
        if (r.getNombreNuits() > SEUIL_VALIDATION_NUITS) {
            setStatutValidation(r);
        }

        reservations.add(r);
        prochainNumero++;
        return r;
    }

    public double annulerReservation(int numero, LocalDateTime maintenant)
            throws ReservationIntrouvableException, OperationReservationInterditeException {
        Reservation r = chercherReservation(numero);
        return r.annuler(maintenant);   // retourne la pénalité éventuelle
    }

    public void afficherReservations() {
        if (reservations.isEmpty()) {
            System.out.println("Aucune réservation.");
            return;
        }
        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }

    private int nombreActives(Client client) {
        int n = 0;
        for (Reservation r : reservations) {
            if (r.getClient() == client && r.isActive()) {
                n++;
            }
        }
        return n;
    }

    /** Place la réservation en attente de validation du responsable. */
    private void setStatutValidation(Reservation r) {
        r.mettreEnAttenteValidation();
    }
}