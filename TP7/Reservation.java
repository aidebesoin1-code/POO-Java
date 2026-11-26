import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private int numero;
    private Client client;
    private Chambre chambre;
    private int nombreNuits;
    private int nombrePersonnes;
    private LocalDate dateArrivee;
    private LocalDate dateDepart;
    private StatutReservation statut;
    private double montantFinal;
    private double penalite;

    public Reservation(int numero, Client client, Chambre chambre, int nombrePersonnes,
                       LocalDate dateArrivee, LocalDate dateDepart)
            throws DatesReservationInvalidesException {

        if (dateArrivee == null || dateDepart == null) {
            throw new DatesReservationInvalidesException("Les dates sont obligatoires.");
        }
        if (!dateDepart.isAfter(dateArrivee)) {
            throw new DatesReservationInvalidesException(
                    "La date de départ (" + dateDepart + ") doit être après la date d'arrivée (" + dateArrivee + ").");
        }
        this.numero = numero;
        this.client = client;
        this.chambre = chambre;
        this.nombrePersonnes = nombrePersonnes;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
        this.nombreNuits = (int) ChronoUnit.DAYS.between(dateArrivee, dateDepart);
        this.statut = StatutReservation.EN_ATTENTE;
    }

    // ---------- Getters ----------
    public int getNumero() { return numero; }
    public Client getClient() { return client; }
    public Chambre getChambre() { return chambre; }
    public int getNombreNuits() { return nombreNuits; }
    public int getNombrePersonnes() { return nombrePersonnes; }
    public LocalDate getDateArrivee() { return dateArrivee; }
    public LocalDate getDateDepart() { return dateDepart; }
    public StatutReservation getStatut() { return statut; }
    public double getMontantFinal() { return montantFinal; }
    public double getPenalite() { return penalite; }

    /** Réservation qui compte dans la limite de 3 réservations actives. */
    public boolean isActive() {
        return statut == StatutReservation.EN_ATTENTE
                || statut == StatutReservation.EN_ATTENTE_VALIDATION
                || statut == StatutReservation.CONFIRMEE
                || statut == StatutReservation.EN_COURS;
    }

    /** Réservation qui bloque la chambre sur ses dates (pour le chevauchement). */
    public boolean bloqueLaChambre() {
        return isActive();
    }

    /** Chevauchement : A.arrivee < B.depart ET B.arrivee < A.depart */
    public boolean chevauche(LocalDate autreArrivee, LocalDate autreDepart) {
        return dateArrivee.isBefore(autreDepart) && autreArrivee.isBefore(dateDepart);
    }

    // ---------- Transitions d'état ----------

    /** Appelée à la création si séjour > 14 nuits. */
    public void mettreEnAttenteValidation() {
        statut = StatutReservation.EN_ATTENTE_VALIDATION;
    }

    /** Appelée par le responsable pour les séjours > 14 nuits. */
    public void validerParResponsable() throws OperationReservationInterditeException {
        if (statut != StatutReservation.EN_ATTENTE_VALIDATION) {
            throw new OperationReservationInterditeException(
                    "Cette réservation n'attend pas de validation (statut : " + statut + ").");
        }
        statut = StatutReservation.EN_ATTENTE;
    }

    public void confirmer() throws OperationReservationInterditeException {
        if (statut == StatutReservation.EN_ATTENTE_VALIDATION) {
            throw new OperationReservationInterditeException(
                    "Séjour de plus de 14 nuits : validation du responsable requise.");
        }
        if (statut != StatutReservation.EN_ATTENTE) {
            throw new OperationReservationInterditeException(
                    "Impossible de confirmer une réservation au statut " + statut);
        }
        statut = StatutReservation.CONFIRMEE;
    }

    /**
     * Annule la réservation et retourne la pénalité (0 si annulation gratuite).
     * Gratuite jusqu'à 48h avant l'arrivée, sinon pénalité = prix d'une nuit.
     */
    public double annuler(LocalDateTime maintenant) throws OperationReservationInterditeException {
        if (statut == StatutReservation.ANNULEE) {
            throw new OperationReservationInterditeException("La réservation est déjà annulée.");
        }
        if (statut == StatutReservation.TERMINEE) {
            throw new OperationReservationInterditeException("Une réservation terminée ne peut pas être annulée.");
        }
        if (statut == StatutReservation.EN_COURS) {
            throw new OperationReservationInterditeException("Une réservation en cours ne peut pas être annulée.");
        }
        if (statut == StatutReservation.NO_SHOW) {
            throw new OperationReservationInterditeException("Une réservation NO_SHOW ne peut pas être annulée.");
        }

        LocalDateTime limiteGratuite = dateArrivee.atStartOfDay().minusHours(48);
        penalite = maintenant.isAfter(limiteGratuite) ? chambre.getPrixParNuit() : 0;
        statut = StatutReservation.ANNULEE;
        return penalite;
    }

    public void checkIn(LocalDateTime maintenant)
            throws OperationReservationInterditeException, ChambreIndisponibleException {
        if (statut != StatutReservation.CONFIRMEE) {
            throw new OperationReservationInterditeException(
                    "Check-in impossible : la réservation est au statut " + statut);
        }
        if (maintenant.isBefore(dateArrivee.atTime(14, 0))) {
            throw new OperationReservationInterditeException(
                    "Check-in possible à partir de 14h00 le jour d'arrivée (" + dateArrivee + ").");
        }
        chambre.reserver();
        statut = StatutReservation.EN_COURS;
    }

    /** Calcule le montant final AVANT de passer à TERMINEE. */
    public double checkOut() throws OperationReservationInterditeException {
        if (statut != StatutReservation.EN_COURS) {
            throw new OperationReservationInterditeException(
                    "Check-out impossible : la réservation est au statut " + statut);
        }
        montantFinal = nombreNuits * chambre.getPrixParNuit();
        statut = StatutReservation.TERMINEE;
        chambre.liberer();
        return montantFinal;
    }

    /** No-show : client absent après 23h59 le jour d'arrivée. */
    public void marquerNoShow(LocalDateTime maintenant) throws OperationReservationInterditeException {
        if (statut != StatutReservation.CONFIRMEE) {
            throw new OperationReservationInterditeException(
                    "NO_SHOW impossible : la réservation est au statut " + statut);
        }
        if (!maintenant.isAfter(dateArrivee.atTime(23, 59))) {
            throw new OperationReservationInterditeException(
                    "Le client peut encore se présenter jusqu'à 23h59 le " + dateArrivee + ".");
        }
        statut = StatutReservation.NO_SHOW;
    }

    @Override
    public String toString() {
        return "Réservation #" + numero + " | " + client.getNom() + " | chambre " + chambre.getNumero()
                + " | " + dateArrivee + " -> " + dateDepart + " (" + nombreNuits + " nuits) | " + statut;
    }
}