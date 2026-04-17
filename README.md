Optimiseur de Budgets

Ce projet est une application Java en ligne de commande conçue pour résoudre le Problème du Sac à Dos Multidimensionnel dans le but d'optimiser la sélection de projets municipaux d'une ville, en respectant plusieurs enveloppes budgétaires tels que économique, social, environnemental, ou par secteurs d'activité .


-Fonctionnalités

Génération de données : Simulation de projets municipaux aléatoires avec différentes contraintes (3 budgets transversaux ou 5 budgets sectoriels).

Analyseur de fichiers : Capacité à lire et traiter des fichiers de données au format académique standard (OR-Library).

Interface Interactive : Menu dans le terminal permettant à l'utilisateur de choisir ses données et l'algorithme à exécuter.


-Algorithmes Implémentés

Le solveur embarque plusieurs heuristiques et métaheuristiques pour trouver la meilleure combinaison d'objets :

1. Algorithmes Gloutons
Glouton par Ajout : Construit la solution en partant d'un sac vide et ajoute les objets les plus rentables, selon un ratio Utilité/Coût, jusqu'à saturation des capacités.

Glouton par Retrait : Part d'un sac contenant tous les objets disponibles, et retire les objets les plus contraignants jusqu'à ce que la solution devienne valide.

2. Algorithmes de Recherche Locale (Hill Climbing)
Hill Climbing Classique : Explore le voisinage complet de la solution actuelle et se déplace toujours vers le meilleur voisin disponible. S'arrête lorsqu'il atteint un optimum local.

Hill Climbing Aléatoire (Stochastic) : Explore des voisins générés aléatoirement (ajout, retrait, échange). Permet une exploration moins prévisible et évite certains calculs lourds.

Multi-Start Hill Climbing (Itératif) : L'approche la plus avancée. Lance plusieurs recherches locales à partir de points de départ stratégiquement différents (sac vide, solution gloutonne, solution aléatoire) pour maximiser les chances de trouver l'optimum global.


-Format de Fichier Supporté

L'application peut lire des instances externes, comme les fichiers .dat. Le fichier texte doit suivre la structure standard suivante :

Nombre d'objets (n)

Nombre de dimensions/budgets (k)

Valeur optimale connue (ignorée par le programme)

Liste des n utilités (bénéfices)

Matrice des coûts dimension par dimension (Ligne 1 = tous les coûts pour le budget 1, etc.)

Liste des k budgets maximaux (capacités)
