package CodeSource.GloutonSolver;
import java.util.Comparator;

import CodeSource.SacADos.Objet;

/**
 * Comparateur personnalisé pour trier les objets dans l'algorithme Glouton
 * <br>
 * Ce comparateur se base sur le coût maximal
 * <br>
 * Il calcule le ratio: {@code utilité/max(coûts)}
 * <br>
 * Les objets sont comparés sur la base de ce ratio pour privilégier ceux qui rapportent le plus de points tout en minimisant leur coût
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class ComparatorMax implements Comparator<Objet>{
    /**
     * Méthode utilitaire pour trouver la valeur maximale dans un tableau d'entiers
     * <br>
     * Utilisée pour identifier le coût le plus élevé d'un objet parmi toutes ses dimensions
     * @param couts Le tableau des coûts de l'objet
     * @return La valeur du coût le plus élevé trouvé dans le tableau
     */
    public static int max(int[] couts) {
        int maximum=Integer.MIN_VALUE;
        for (int cout: couts){
            if(cout>maximum){
                maximum=cout;
            }
        }
        return maximum;
    }
    /**
     * Compare deux objets pour déterminer leur ordre de priorité
     * <br>
     * Le tri est effectué en fonction du ratio {@code obj=utilité/max(coûts)}
     * <br>
     * Si obj1 &lt; obj2 : renvoie -1
     * <br>
     * Si obj1 &gt; obj2 : renvoie 1
     * <br>
     * Si obj1==obj2: renvoie 0
     * @param obj1 Le premier objet à comparer
     * @param obj2 Le second objet à comparer
     * @return Un entier négatif, zéro ou positif selon que le premier argument est inférieur, égal ou supérieur au second
     */
    @Override
    public int compare(Objet obj1, Objet obj2){
        double v1=obj1.getUtilite()/max(obj1.getCouts());
        double v2=obj2.getUtilite()/max(obj2.getCouts());
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