package CodeSource.SacADos;

/**
 * Représente un objet dans le problème du sac à dos multidimensionnel
 * <br>
 * Chaque objet est caractérisé par:
 * <br>
 * Une utilité: la valeur qu'il apporte s'il est sélectionné
 * <br>
 * Des coûts: un tableau représentant la consommation de ressources de l'objet dans les différentes dimensions
 * <br>
 * La classe garantit la cohérence des données en interdisant les valeurs négatives
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class Objet{
    /** La valeur ou le gain apporté par cet objet */
    private int utilite;
    /** Tableau des coûts de l'objet */
    private int[] couts;

    /**
     * Construit un nouvel objet avec une utilité et des coûts définis
     * <br>
     * Ce constructeur vérifie qu'aucune valeur n'est négative, car cela n'aurait pas de sens dans ce problème
     * @param utilite La valeur de l'objet
     * @param couts Le tableau des coûts dans chaque dimension
     * @throws ErrNegatif Si l'utilité est négative ou si l'un des coûts du tableau est négatif
     */
    public Objet(int utilite, int[] couts) throws ErrNegatif{
        if(utilite<0){
            throw new ErrNegatif("L'utilité ne peut pas être négatif");
        }
        this.utilite=utilite;
        for(int i: couts){
            if(i<0){
                throw new ErrNegatif("Un objet ne peut pas avoir un coût négatif");
            }
        }
        this.couts=couts;
    }
    /**
     * Récupère l'utilité de l'objet
     * @return La valeur de l'utilité
     */
    public int getUtilite(){
        return utilite;
    }
    /**
     * Récupère les coûts de l'objet
     * @return Un tableau d'entiers représentant les coûts
     */
    public int[] getCouts(){
        return couts;
    }
}