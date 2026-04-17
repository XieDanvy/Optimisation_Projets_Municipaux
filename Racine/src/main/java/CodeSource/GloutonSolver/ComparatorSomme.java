package CodeSource.GloutonSolver;
import java.util.Comparator;

import CodeSource.SacADos.Objet;

/**
 * Comparateur personnalisé pour trier les objets dans l'algorithme Glouton
 * <br>
 * Ce comparateur se base sur le cumul des coûts
 * <br>
 * Il calcule le ratio: {@code Utilité/Somme(coûts)}
 * <br>
 * Les objets sont comparés sur la base de ce ratio pour privilégier ceux qui rapportent le plus de points par rapport à la consommation totale de ressources
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class ComparatorSomme implements Comparator<Objet> {
    /**
     * Méthode utilitaire pour calculer la somme des éléments d'un tableau d'entiers
     * <br>
     * Utilisée pour obtenir le coût total d'un objet sur toutes ses dimensions
     * @param couts Le tableau des coûts de l'objet
     * @return La somme des coûts sous forme de nombre décimal
     */
    public static double somme(int[] couts) {
        double s=0;
        for(int cout: couts){
            s+=cout;
        }
        return s;
    }
    /**
     * Compare deux objets pour déterminer leur ordre de priorité
     * <br>
     * Le tri est effectué en fonction du ratio {@code obj = utilité / somme(coûts)}
     * <br>
     * Si obj1 &lt; obj2 : renvoie -1
     * <br>
     * Si obj1 &gt; obj2 : renvoie 1
     * <br>
     * Si obj1==obj2: renvoie 0
     * @param obj1 Le premier objet à comparer
     * @param obj2 Le second objet à comparer
     * @return Un entier négatif, zéro ou positif selon le résultat de la comparaison des ratios
     */
    @Override
    public int compare(Objet obj1, Objet obj2){
        double v1=obj1.getUtilite()/somme(obj1.getCouts());
        double v2=obj2.getUtilite()/somme(obj2.getCouts());
        if (v1<v2){
            return -1;
        } 
        else if (v1>v2){
            return 1;
        } 
        else{
            return 0;
        }
    }
}