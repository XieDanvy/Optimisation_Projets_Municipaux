package CodeSource.Equipe;

/**
 * Classe abstraite définissant les attributs et méthodes communes à tous les membres de l'équipe municipale
 * <br>
 * Elle impose une structure de base: Nom, Prénom, Age
 * <br>
 * Force chaque sous-classe à définir sa propre méthode {@code évaluer}
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public abstract class Equipe{
    /** Le nom de famille du membre de l'équipe */
    protected String nom;
    /** Le prénom du membre de l'équipe */
    protected String prenom;
    /** L'âge du membre de l'équipe. */
    protected int age;

    /**
     * Constructeur initialisant les informations personnelles d'un membre de l'équipe
     * @param nom Le nom de famille
     * @param prenom Le prénom
     * @param age L'âge de la personne
     * @throws ErrAge Si l'âge fourni est négatif
     */
    public Equipe(String nom, String prenom, int age) throws ErrAge{
        this.nom=nom;
        this.prenom=prenom;
        if(age<0){
            throw new ErrAge("Veuillez rentrer un age correct");
        }
        this.age=age;
    }

    /**
     * Récupère le nom du membre
     * @return Le nom de famille
     */
    public String getNom(){
        return nom;
    }

    /**
     * Récupère le prénom du membre
     * @return Le prénom
     */
    public String getPrenom(){
        return prenom;
    }

    /**
     * Récupère l'âge du membre
     * * @return L'âge
     */
    public int getAge(){
        return age;
    }

    /**
     * Méthode abstraite définissant l'action d'évaluation d'un projet
     * <br>
     * Chaque type de membre devra implémenter cette méthode
     * * @param projet Le projet concerné par l'action
     */
    public abstract void evaluer(Projet projet);
}