package CodeSource.Equipe;

/**
 * Représente un projet municipal proposé par un expert
 * <br>
 * Cette classe centrale stocke toutes les informations d'un projet: titre, description, secteur, utilité, coûts social, coûts économique, coûts environnemental
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class Projet{
    /** Le titre du projet. */
    private final String titre;
    /** Une description du projet */
    private final String description;
    /** Le secteur d'activité auquel le projet appartient */
    private final Secteur secteur;
    /** La valeur d'utilité du projet */
    private int benefice;
    /** Le coût social estimé */
    private int coutSocial;
    /** Le coût économique estimé */
    private int coutEco;
    /** Le coût environnemental estimé */
    private int coutEnviron;

    /**
     * Construit un nouveau projet avec ses informations de base
     * <br>
     * À la création, tous les coûts et le bénéfice sont initialisés à 0, ils seront mis à jour plus tard lors de la phase d'évaluation
     * @param titre Le titre du projet
     * @param description La description du projet
     * @param secteur Le secteur d'activité du projet
     */
    public Projet(String titre,String description,Secteur secteur){
        this.titre=titre;
        this.description=description;
        this.secteur=secteur;
        this.benefice=0;
        this.coutSocial=0;
        this.coutEco=0;
        this.coutEnviron=0;
    }

    /**
     * Définit le bénéfice du projet
     * <br>
     * Cette méthode est généralement appelée par l'Élue
     * @param benefice La valeur du bénéfice
     */
    public void Benefice(int benefice){
        this.benefice=benefice;
    }

    /**
     * Définit le coût social du projet
     * <br>
     * Cette méthode est appelée par un Évaluateur spécialisé "social"
     * @param coutSocial Le coût social estimé
     */
    public void coutSocial(int coutSocial){
        this.coutSocial=coutSocial;
    }

    /**
     * Définit le coût économique du projet
     * <br>
     * Cette méthode est appelée par un Évaluateur spécialisé "économique"
     * @param coutEco Le coût économique estimé.
     */
    public void coutEco(int coutEco){
        this.coutEco=coutEco;
    }

    /**
     * Définit le coût environnemental du projet
     * <br>
     * Cette méthode est appelée par un Évaluateur spécialisé "environnemental"
     * @param coutEnviron Le coût environnemental estimé
     */
    public void coutEnviron(int coutEnviron){
        this.coutEnviron=coutEnviron;
    }

    /**
     * Récupère le titre du projet
     * @return Le titre
     */
    public String getTitre(){
        return titre;
    }

    /**
     * Récupère la description du projet
     * @return La description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Récupère le secteur du projet
     * @return L'objet Secteur associé
     */
    public Secteur getSecteur(){
        return secteur;
    }

    /**
     * Récupère le bénéfice du projet
     * @return Le bénéfice
     */
    public int getBenefice(){
        return benefice;
    }

    /**
     * Récupère le coût social du projet
     * @return Le coût social
     */
    public int getCoutSocial(){
        return coutSocial;
    }

    /**
     * Récupère le coût économique du projet
     * @return Le coût économique
     */
    public int getCoutEco(){
        return coutEco;
    }

    /**
     * Récupère le coût environnemental du projet
     * @return Le coût environnemental
     */
    public int getCoutEnviron(){
        return coutEnviron;
    }
}