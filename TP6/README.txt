TP POO - Gestion d'une université
=================================

Version : Java 17 ou supérieur
Aucune bibliothèque externe n'est nécessaire.

Compilation :
    javac *.java

Exécution :
    java Main

Classes :
- Etudiant : informations d'un étudiant et relation avec sa filière / ses groupes.
- Filiere : collection d'étudiants et composition de quatre semestres.
- Semestre : modules et groupes d'un semestre.
- Module : module réutilisable dans plusieurs semestres.
- Enseignant : enseignant et liste de ses enseignements.
- Enseignement : association entre enseignant, module, groupe et nombre d'heures.
- Groupe : étudiants affectés à un groupe pour un semestre.
- Universite : registre des filières et contrôle de l'unicité globale des matricules.
- Main : démonstration de toutes les fonctionnalités.

Remarque sur l'énoncé :
La phrase « Une filière possède au maximum ... » est incomplète dans le PDF.
La valeur est donc paramétrée dans Filiere.MAX_ETUDIANTS (500 par défaut).
Modifiez cette constante selon la valeur donnée oralement par l'enseignant.

Modélisation :
- Filiere / Etudiant : agrégation (un étudiant existe indépendamment de sa filière).
- Filiere / Semestre : composition (les semestres sont créés avec la filière).
- Semestre / Module : agrégation (un module peut être partagé par plusieurs filières).
- Enseignant / Module : association plusieurs-à-plusieurs, portée par Enseignement.
- Semestre / Groupe : composition dans cette proposition.
- Etudiant / Groupe : affectation limitée à un seul groupe par semestre.
