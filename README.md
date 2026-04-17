# Optimiseur de Budgets Municipaux (Solveur MKP)

**Optimiseur de Budgets** est une application Java conçue pour résoudre le **Problème du Sac à Dos Multidimensionnel (MKP)**. Cet outil permet d'optimiser la sélection de projets municipaux en respectant plusieurs enveloppes budgétaires (économique, social, environnemental, etc.).

## Description du Projet

L'application aide à la prise de décision complexe : choisir la meilleure combinaison de projets pour maximiser l'utilité globale tout en respectant des contraintes budgétaires transversales ou sectorielles.

## Fonctionnalités

* **Génération de données :** Simulation de projets municipaux avec 3 budgets transversaux ou 5 budgets sectoriels.
* **Analyseur de fichiers :** Lecture et traitement de fichiers au format académique standard (**OR-Library**).
* **Interface Interactive :** Menu dans le terminal pour configurer les données et choisir les algorithmes.

## Algorithmes Implémentés

Le solveur intègre plusieurs stratégies de résolution :
* **Heuristiques Gloutonnes :** Méthodes par Ajout (partir de vide) ou par Retrait (partir du plein).
* **Recherche Locale (Hill Climbing) :** Versions Classique, Aléatoire (Stochastic) et **Multi-Start** (itératif) pour éviter les optima locaux.

## Format de Fichier Supporté

L'application accepte les fichiers `.dat` structurés comme suit :
1. Nombre d'objets (`n`)
2. Nombre de dimensions (`k`)
3. Valeur optimale (ignorée)
4. Liste des utilités, Matrice des coûts, et Capacités budgétaires.

## Installation et Utilisation

### Prérequis
* Avoir installé le **JDK Java** (version 8 ou supérieure).

### Installation
Clonez le dépôt sur votre machine locale :
```bash
git clone [https://github.com/XieDanvy/Optimisation_Projets_Municipaux.git](https://github.com/XieDanvy/Optimisation_Projets_Municipaux.git)
cd Optimisation_Projets_Municipaux
```
Compiler les fichiers sources :
```bash
javac src/*.java -d bin
```
Lancez le programme principal :
```bash
java -cp bin Principal
```

## Installation et Utilisation

Projet développé par Xie Danvy.
