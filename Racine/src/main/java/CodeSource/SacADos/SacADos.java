package CodeSource.SacADos;
import java.util.List;
import CodeSource.Equipe.*;


/**
 * Représente une sac à dos multidimensionnel
 * <br>
 * Cette classe définit le problème à résoudre:
 * <br>
 * La dimension du problème, c'est-à-dire le nombre de contrainte
 * <br>
 * Les budgets pour chaque dimension
 * <br>
 * La liste des objets disponibles pour être mis dans le sac
 * <br>
 * Elle assure aussi la cohérence des données lors de l'initialisation
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class SacADos{
    /** Le nombre de dimensions */
    private int dimension;
    /** Tableau contenant la capacité maximale pour chaque dimension */
    private int[] budgets;
    /** Liste des objets disponibles */
    private List<Objet> objets;

    /**
     * Construit une nouvelle instance du problème de Sac à Dos
     * <br>
     * Ce constructeur effectue les vérifications suivantes:
     * <br>
     * -La dimension doit être positive
     * <br>
     * -Chaque objet de la liste doit avoir exactement le même nombre de coûts que la dimension déclarée
     * <br>
     * -Les budgets ne peuvent pas être négatifs
     * <br>
     * -Le tableau des budgets doit avoir une taille égale à la dimension déclarée
     * @param dimension Le nombre de dimension
     * @param budgets Le tableau des capacités maximales pour chaque dimension
     * @param objets La liste initiale des objets
     * @throws ErrNegatif Si la dimension ou l'un des budgets est négatif
     * @throws ErrDimension Si la taille du tableau des budgets ou le nombre de coûts d'un objet ne correspond pas à la dimension indiquée
     */
    public SacADos(int dimension, int[] budgets, List<Objet> objets) throws ErrNegatif,ErrDimension{
        if(dimension<0){
            throw new ErrNegatif("Une dimension ne peut pas être négatif");
        }
        for(Objet obj: objets){
            if(obj.getCouts().length!=dimension){
                throw new ErrDimension("La dimension du sac est incompatible avec celui des objets");
            }
        }
        for(int i: budgets){
            if(i<0){
                throw new ErrNegatif("Un budget ne peut pas être negatif");
            }
        }
        if(budgets.length!=dimension){
            throw new ErrDimension("Le budget ne correspond pas à la dimension attendue");
        }
        this.dimension=dimension;
        this.budgets=budgets;
        this.objets=objets;
    }
    /**
     * Ajoute un objet à la liste des objets
     * @param obj L'objet à ajouter
     */
    public void ajouterObjet(Objet obj){
        objets.add(obj);
    }
    /**
     * Retire un objet de la liste des objets
     * @param obj L'objet à supprimer
     */
    public void supprimerObjet(Objet obj){
        objets.remove(obj);
    }
    /**
     * Récupère les budgets du sac
     * @return Le tableau des budgets
     */
    public int[] getBudgets(){
        return budgets;
    }
    /**
     * Récupère la dimension du sac
     * @return Le nombre de dimension
     */
    public int getDimension(){
        return dimension;
    }
    /**
     * Récupère la liste des objets
     * @return La liste des objets
     */
    public List<Objet> getObjets(){
        return objets;
    }
    
}