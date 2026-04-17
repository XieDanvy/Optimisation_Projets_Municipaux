package CodeSource.Equipe;
import java.util.Random;

/**
 * Représente une élue de l'équipe municipale
 * <br>
 * Son rôle est d'évaluer les projets en leur attribuant un score d'utilité, représenté par le bénéfice
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class Elue extends Equipe{

    /**
     * Construit une nouvelle élue
     * @param nom Le nom de l'élue
     * @param prenom Le prénom de l'élue
     * @param age L'âge de l'élue
     * @throws ErrAge Si l'âge indiqué ne respecte pas les critères
     */
    public Elue(String nom, String prenom, int age) throws ErrAge{
        super(nom,prenom,age);
    }

    /**
     * Évalue un projet en lui attribuant une valeur de bénéfice
     * <br>
     * Le bénéfice est généré aléatoirement, valeur entre 0 et 199
     * @param projet Le projet à évaluer
     */
    @Override
    public void evaluer(Projet projet){
        Random random=new Random();
        int benefice=random.nextInt(200);
        projet.Benefice(benefice);
    }
}