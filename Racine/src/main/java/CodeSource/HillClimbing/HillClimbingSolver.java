package CodeSource.HillClimbing;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import CodeSource.SacADos.Objet;
import CodeSource.SacADos.SacADos;

/**
 * Classe implémentant les méthodes de résolution Hill Climbing par recherche locale ainsi que ses variantes par recherche aléatoire et itérative
 * <br>
 * Cela permet d'optimiser le choix des objets dans le sac à dos en explorant les solutions voisines à la solution initiale
 * @author Xie Danvy
 * @author Megueddem Shirine
 */

public class HillClimbingSolver{
    /** Sac à dos qu'on cherche à optimiser */
    private final SacADos sac;
    /** Paramètre définissant la taille du voisinage, si t=1, on change 1 objet, si t=3, on en change 3*/
    private final int t;
    /** Nombre maximum de tentatives sans amélioration */
    private final int nbTentative;
    /** Générateur de nombres aléatoires pour la variante à voisin aléatoire */
    private final Random random;

    /**
     * Constructeur du solveur Hill Climbing
     * @param sac Sac à dos à optimiser
     * @param t Paramètre déterminant la taille du voisinage
     * @param nbTentative Nombre maximum d'essais pour sortir d'un optimum local
     */
    public HillClimbingSolver(SacADos sac, int t, int nbTentative){
        this.sac=sac;
        this.t=t;
        this.nbTentative=nbTentative;
        this.random=new Random();
    }

    /**
     * Calcule la somme des utilités des objets présents dans la solution donnée
     * @param solution La liste des objets sélectionnés
     * @return La somme totale des utilités
     */
    public int fonctionUtilite(List<Objet> solution){
        int utiliteTotal=0;
        for(Objet obj: solution){
            utiliteTotal+=obj.getUtilite();
        }
        return utiliteTotal;
    }

    /**
     * Vérifie si une solution respecte toutes les contraintes de budget
     * @param solution La liste des objets à vérifier
     * @return true si la solution respecte tous les budgets, false sinon
     */
    private boolean contrainteCout(List<Objet> solution){
        int[] coutTotal=new int[sac.getDimension()];

        for(Objet obj: solution){
            int[] cout=obj.getCouts();
            for(int i=0; i<cout.length; i++){
                coutTotal[i]+=cout[i];
            }
        }
        int[] budgets=sac.getBudgets();

        for(int i=0; i<budgets.length; i++){
            if(coutTotal[i]>budgets[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Génère le voisinage complet avec 't' récurrence en utilisant une boucle itérative
     * <br>
     * Si t=1, renvoie les voisins directs, si t=2, renvoie les voisins des voisins, etc...
     * @param actuelSolution La solution de départ
     * @return La liste de tous les voisins trouvés à au plus une distance t
     */
    private List<List<Objet>> creationVoisinageT(List<Objet> actuelSolution){
        List<List<Objet>> solutionsVoulu=new ArrayList<>();
        solutionsVoulu.add(actuelSolution);

        for(int i=0; i<t; i++){
            List<List<Objet>> nouveauxVoisins=new ArrayList<>();

            for (List<Objet> sol : solutionsVoulu) {
                List<List<Objet>> voisinsSolution=creationVoisinage(sol);
                nouveauxVoisins.addAll(voisinsSolution);
            }
            solutionsVoulu=nouveauxVoisins;
        }
        return solutionsVoulu;
    }

    /**
     * Génère tous les voisins possibles d'une solution
     * <br>
     * Les voisins sont créé par ajout d'un objet, retrait d'un objet, ou échange d'un objet par un autre
     * @param actuelSolution répresente la solution de départ
     * @return Une liste contenant toutes les solutions voisines possibles
     */
    private List<List<Objet>> creationVoisinage(List<Objet> actuelSolution) {
        List<List<Objet>> voisinage=new ArrayList<>();
        List<Objet> allObjects=sac.getObjets();

        for(Objet obj: allObjects){
            if(!actuelSolution.contains(obj)){
                List<Objet> voisin=new ArrayList<>(actuelSolution);
                voisin.add(obj);
                voisinage.add(voisin);
            }
        }
        
        for (Objet obj: actuelSolution) {
            List<Objet> voisin=new ArrayList<>(actuelSolution);
            voisin.remove(obj);
            voisinage.add(voisin);
        }
        
        for(Objet objSupp: actuelSolution){
            for(Objet objAjoute: allObjects){
                if(!actuelSolution.contains(objAjoute)){
                    List<Objet> voisin=new ArrayList<>(actuelSolution);
                    voisin.remove(objSupp);
                    voisin.add(objAjoute);
                    voisinage.add(voisin);
                }
            }
        }
        return voisinage;
    }

    /**
     * Génère un unique voisin aléatoire en appliquant 't' modifications successives
     * <br>
     * Choisit au hasard entre ajout, retrait ou échange
     * @param actuelSolution La solution de départ
     * @return Une nouvelle liste d'objets représentant la solution voisine générée
     */
    private List<Objet> creationVoisinageRandom(List<Objet> actuelSolution) {
        List<Objet> allObjects=sac.getObjets();
        List<Objet> voisin=new ArrayList<>(actuelSolution);
        int operation=random.nextInt(3);

        if (operation==0){
            List<Objet> newObjects=new ArrayList<>();

            for(Objet obj: allObjects){
                if(!voisin.contains(obj)){
                    newObjects.add(obj);
                }
            }

            if(!newObjects.isEmpty()){
                int index=random.nextInt(newObjects.size());
                voisin.add(newObjects.get(index));
            }
        } 

        else if(operation==1){
            if(!voisin.isEmpty()){
                int index=random.nextInt(voisin.size());
                voisin.remove(index);
            }
        } 

        else{
            if(!voisin.isEmpty() && allObjects.size()>voisin.size()){
                int indexRemove=random.nextInt(voisin.size());
                voisin.remove(indexRemove);
                
                List<Objet> newObjects=new ArrayList<>();

                for(Objet obj: allObjects){
                    if(!voisin.contains(obj)){
                        newObjects.add(obj);
                    }
                }

                if(!newObjects.isEmpty()){
                    int indexAdd=random.nextInt(newObjects.size());
                    voisin.add(newObjects.get(indexAdd));
                }
            }
        }
        return voisin;
    }

    /**
     * Algorithme Hill Climbing classique
     * <br>
     * Explore tout le voisinage à chaque étape et se déplace vers le meilleur voisin
     * <br>
     * S'arrête lorsqu'aucun voisin n'est meilleur après un nombre donné de tentatives (nbTentative)
     * @param initialSolution La solution de départ
     * @return La meilleure solution trouvée
     */
    public List<Objet> solverClassic(List<Objet> initialSolution) {
        List<Objet> actuelSolution=new ArrayList<>(initialSolution);
        int tentative=0;
        
        while(tentative<nbTentative){
            List<List<Objet>> voisinage=creationVoisinageT(actuelSolution);
            List<List<Objet>> bonVoisinage=new ArrayList<>();

            for(List<Objet> voisin: voisinage){
                if(contrainteCout(voisin)){
                    bonVoisinage.add(voisin);
                }
            }
            
            List<Objet> meilleurVoisin=null;
            int meilleurUtilite=fonctionUtilite(actuelSolution);
            
            for(List<Objet> voisin: bonVoisinage){
                int voisinUtilite=fonctionUtilite(voisin);
                if(voisinUtilite>meilleurUtilite){
                    meilleurUtilite=voisinUtilite;
                    meilleurVoisin=voisin;
                }
            }
            
            if(meilleurVoisin!=null){
                actuelSolution=meilleurVoisin;
                tentative=0;
            } 
            else{
                tentative++;
            }
        }
        return actuelSolution;
    }

    /**
     * Algorithme Hill Climbing avec voisiniage aléatoire
     * <br>
     * Génère un voisin au hasard et l'accepte s'il est meilleur
     * @param initialSolution La solution de départ
     * @return La meilleure solution trouvée
     */
    public List<Objet> solverRandom(List<Objet> initialSolution){
        List<Objet> actuelSolution=new ArrayList<>(initialSolution);
        int actuelUtilite=fonctionUtilite(actuelSolution);
        
        for(int i=0; i<nbTentative; i++){
            List<Objet> voisin=creationVoisinageRandom(actuelSolution);
            
            if(contrainteCout(voisin)){
                int voisinUtilite=fonctionUtilite(voisin);
                if(voisinUtilite>actuelUtilite){
                    actuelSolution=voisin;
                    actuelUtilite=voisinUtilite;
                }
            }
        }
        return actuelSolution;
    }

    /**
     * Algorithme Hill Climbing Itératif
     * <br>
     * Lance l'algorithme classique plusieurs fois à partir de points de départ différents
     * <br>
     * Cela permet d'explorer diverses solution pour éviter les optimums locaux
     * @param initialSolutions La solution de départ
     * @return La meilleure solution globale trouvée parmi toutes les tentatives
     */
    public List<Objet> solverIterative(List<List<Objet>> initialSolutions){
        List<Objet> meilleurSolution=null;
        int meilleurUtilite=Integer.MIN_VALUE;
        
        for(List<Objet> initialSolution: initialSolutions){
            List<Objet> solution=solverClassic(initialSolution);
            int utilite=fonctionUtilite(solution);
            if(utilite>meilleurUtilite){
                meilleurUtilite=utilite;
                meilleurSolution=solution;
            }
        }
        return meilleurSolution;
    }
}