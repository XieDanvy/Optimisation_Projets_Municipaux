package CodeSource.GloutonSolver;

import java.util.ArrayList;
import java.util.List;

import CodeSource.SacADos.*;
/**
 * Classe principale de test spécifique pour les algorithmes Gloutons
 * <br>
 * Cette classe permet de vérifier le bon fonctionnement des solveurs
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class MainGlouton {
    /**
     * Point d'entrée du programme de test glouton
     * <br>
     * Le scénario exécuté est le suivant:
     * <br>
     * -Création manuelle de d'objets avec des coûts et utilités variés
     * <br>
     * -Définition d'un sac à dos avec 3 budgets
     * <br>
     * -Exécution du solveur par Ajout avec le ComparatorSomme
     * <br>
     * -Exécution du solveur par Retrait avec le ComparatorMax
     * <br>
     * -Affichage des objets retenus pour chaque méthode dans la console
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        try {
            Objet obj1=new Objet(10, new int[]{3, 2, 4});
            Objet obj2=new Objet(20, new int[]{6, 4, 2});
            Objet obj3=new Objet(15, new int[]{5, 3, 1});
            Objet obj4=new Objet(8,  new int[]{2, 1, 5});

            List<Objet> objets = new ArrayList<>();
            objets.add(obj1);
            objets.add(obj2);
            objets.add(obj3);
            objets.add(obj4);

            int[] budgets={10, 7, 9};
            SacADos sac=new SacADos(3, budgets, objets);

            System.out.println("Par la méthode gloutonne à ajout");
            GloutonAjouterSolver sacAjout=new GloutonAjouterSolver(sac);
            List<Objet> solutionAjout=sacAjout.SolverAjout(sac, new ComparatorSomme());

            System.out.println("Objets ajoutés dans le sac:");
            for (Objet obj: solutionAjout) {
                System.out.println(obj);
            }

            System.out.println("\n Par la méthode gloutonne à retrait");
            GloutonRetraitSolver sacRetrait=new GloutonRetraitSolver(sac);
            List<Objet> solutionRetrait=sacRetrait.SolverRetrait(sac, new ComparatorMax());

            System.out.println("Objets restants dans le sac:");
            for (Objet obj: solutionRetrait) {
                System.out.println(obj);
            }

        }

        catch(ErrNegatif e){
            System.out.println("Une variable est négatif, ce qui est impossbile");
        }

        catch(ErrDimension e){
            System.out.println("La dimension du sac ou des objets est incorrecte");
        }
         
        catch(Exception e) {
            System.out.println("Un erreur est suvenue");
            e.printStackTrace();
        }
    }
}