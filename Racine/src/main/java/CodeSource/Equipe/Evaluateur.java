package CodeSource.Equipe;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Représente un évaluateur membre de l'équipe municipale
 * <br>
 * Chaque évaluateur possède une spécialisation unique: économique, social ou environnemental
 * <br>
 * Son rôle est d'estimer le coût d'un projet dans son domaine de compétence
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class Evaluateur extends Equipe{
    /** Le domaine de spécialisation de l'évaluateur */
    private String specialisation;
    /** Liste statique des spécialisations autorisées: économique, social, environnemental */
    private static final List<String> listeSpe=Arrays.asList("économique","social","environnemental");

    /**
     * Construit un nouvel évaluateur
     * <br>
     * Vérifie que la spécialisation fournie fait partie de la liste des domaines valides
     * @param nom Le nom de l'évaluateur
     * @param prenom Le prénom de l'évaluateur
     * @param age L'âge de l'évaluateur
     * @param specialisation Le domaine de compétence
     * @throws ErrSpe Si la spécialisation n'est pas reconnue dans la liste autorisée
     * @throws ErrAge Si l'âge est invalide
     */
    public Evaluateur(String nom, String prenom, int age, String specialisation) throws ErrSpe,ErrAge{
        super(nom,prenom,age);
        if(!listeSpe.contains(specialisation)){
            throw new ErrSpe("Veuillez entrer une spécialisation valide");
        }
        this.specialisation=specialisation;
    }

    /**
     * Récupère la spécialisation de l'évaluateur
     * @return Le nom du domaine de compétence
     */
    public String getSpecialisation(){
        return specialisation;
    }

    /**
     * Évalue le coût d'un projet selon la spécialisation de l'évaluateur
     * <br>
     * Cette méthode génère un coût aléatoire compris entre 1 et 100
     * <br>
     * Ensuite, elle met à jour l'attribut correspondant du projet: CoutEco, CoutSocial ou CoutEnviron, en fonction de la spécialisation de cet évaluateur.
     * @param projet Le projet dont le coût doit être estimé
     */
    @Override
    public void evaluer(Projet projet){
        Random random=new Random();
        if(specialisation.equals("économique")){
            int coutEco=random.nextInt(100);
            coutEco+=1;
            projet.coutEco(coutEco);
        }
        else if(specialisation.equals("social")){
            int coutSocial=random.nextInt(100);
            coutSocial+=1;
            projet.coutSocial(coutSocial);
        }
        else if(specialisation.equals("environnemental")){
            int coutEnviron=random.nextInt(100);
            coutEnviron+=1;
            projet.coutEnviron(coutEnviron);
        }
    }
}