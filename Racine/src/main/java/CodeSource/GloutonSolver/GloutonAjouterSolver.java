package CodeSource.GloutonSolver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import CodeSource.SacADos.Objet;
import CodeSource.SacADos.SacADos;

/**
 * Implémente l'algorithme Glouton par Ajout
 * <br>
 * Le principe est de partir d'une solution vide et d'ajouter les objets un par un en commençant par les meilleurs en respectant les contraintes de budgets le permettent
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class GloutonAjouterSolver{
    /** L'instance du problème de sac à dos à résoudre */
    private final SacADos sac;

    /**
     * Constructeur du solveur glouton par ajout
     * @param sac L'instance du problème contenant les objets et les budgets
     */
    public GloutonAjouterSolver(SacADos sac){
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
     * Exécute la résolution gloutonne
     * <br>
     * L'algorithme suit ces étapes:
     * <br>
     * -Copie tous les objets disponibles
     * <br>
     * -Trie ces objets selon le comparateur fourni
     * <br>
     * -Inverse la liste pour avoir les objets les plus rentables en premier
     * <br>
     * -Parcourt la liste triée et ajoute l'objet au sac si et seulement si tous les budgets sont respectés
     * @param sac Le problème à résoudre
     * @param ComparatorSomme Le comparateur définissant le tri
     * @return La liste des objets sélectionnés qui constituent la solution
     */
    public List<Objet> SolverAjout(SacADos sac, Comparator<Objet> ComparatorSomme){
        List<Objet> trieObjet=new ArrayList<>();
        for(Objet obj: sac.getObjets()){
            trieObjet.add(obj);
        }
        Collections.sort(trieObjet, ComparatorSomme);
        Collections.reverse(trieObjet);
        int[] budgetSac=sac.getBudgets().clone();
        List<Objet> sacOpti=new ArrayList<>();
        for(Objet obj: trieObjet){
            int[] cout=obj.getCouts();
            boolean possible=true;
            for(int i=0;i<budgetSac.length;i++){
                if(cout[i]>budgetSac[i]){
                    possible=false;
                    break;
                    }
                }
            if(possible){
                for(int j=0;j<budgetSac.length;j++){
                    budgetSac[j]-=cout[j];
                }
                sacOpti.add(obj);
            }
        }
        return sacOpti;
    }
}