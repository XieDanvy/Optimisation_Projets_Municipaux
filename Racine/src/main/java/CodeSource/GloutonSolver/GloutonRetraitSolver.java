package CodeSource.GloutonSolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import CodeSource.SacADos.Objet;
import CodeSource.SacADos.SacADos;

/**
 * Implémente l'algorithme Glouton par Retrait
 * <br>
 * Cet algorithme commence par remplir le sac avec tous les objets disponibles
 * <br>
 * Il retire ensuite les objets les moins intéressants un par un, jusqu'à ce que les contraintes de budget soient respectées
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class GloutonRetraitSolver{
    /** L'instance du problème de Sac à Dos à résoudre */
    private final SacADos sac;
    /**
     * Constructeur du solveur glouton par retrait
     * @param sac L'instance du problème contenant les objets et les budgets
     */
    public GloutonRetraitSolver(SacADos sac){
        this.sac=sac;
    }

    /**
     * Récupère le sac
     * @return L'objet SacADos
     */
    public SacADos getSac(){
        return sac;
    }
    /**
     * Vérifie si les coûts totaux actuels respectent les budgets du sac
     * <br>
     * Cette méthode sert de condition d'arrêt pour la boucle de retrait
     * @param coutTotal Le tableau des coûts cumulés des objets actuellement dans le sac
     * @param budgetSac Le tableau des budgets maximaux autorisés
     * @return {@code true} si tous les coûts sont inférieurs ou égaux aux budgets, {@code false} sinon.
     */
    public boolean budgetContrainte(int[] coutTotal, int[] budgetSac){
        for(int i=0;i<budgetSac.length;i++){
            if(coutTotal[i]>budgetSac[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * Exécute la résolution gloutonne
     * <br>
     * L'algorithme suit ces étapes:
     * <br>
     * -Sélectionne initialement tous les objets
     * <br>
     * -Calcule la somme totale des coûts
     * <br>
     * -Trie les objets par ordre croissant d'efficacité
     * <br>
     * -Tant que le budget n'est pas respecté, on retire l'objet à l'index 0, qui est le pire, et on déduit ses coûts
     * @param sac Le problème à résoudre
     * @param comparatorMax Le comparateur utilisé pour identifier les objets les moins utiles
     * @return La liste des objets restants qui rentrent dans le sac
     */
    public List<Objet> SolverRetrait(SacADos sac, Comparator<Objet> comparatorMax){
        List<Objet> trieObjet=new ArrayList<>();
        for(Objet obj: sac.getObjets()){
            trieObjet.add(obj);
        }
        Collections.sort(trieObjet, comparatorMax);
        int[] budgetSac=sac.getBudgets().clone();
        int[] coutTotal=new int[budgetSac.length];
        for(Objet obj: trieObjet){
            int[] coutObj=obj.getCouts();
            for(int i=0;i<budgetSac.length;i++){
                coutTotal[i]+=coutObj[i];
            }
        }
        while(!budgetContrainte(coutTotal, budgetSac) && !trieObjet.isEmpty()){
            Objet obj=trieObjet.get(0);
            int[] coutObj=obj.getCouts();
            for(int j=0;j<budgetSac.length;j++){
                coutTotal[j]-=coutObj[j];
            }
            trieObjet.remove(0);
        }
        return trieObjet;
    }
}