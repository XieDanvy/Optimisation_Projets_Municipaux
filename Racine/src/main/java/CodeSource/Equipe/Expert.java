package CodeSource.Equipe;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Représente un expert membre de l'équipe municipale
 * <br>
 * Le rôle de l'expert est de proposer de nouveaux projets en fonction de ses domaines de compétence
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class Expert extends Equipe{
    /** Liste des secteurs maîtrisés par cet expert */
    private List<Secteur> expertise= new ArrayList<>();

    /**
     * Construit un nouvel expert avec ses domaines de compétence
     * @param nom Le nom de l'expert
     * @param prenom Le prénom de l'expert
     * @param age L'âge de l'expert
     * @param expertise La liste des secteurs que cet expert maîtrise
     * @throws ErrAge Si l'âge indiqué est invalide
     */
    public Expert(String nom, String prenom, int age, List<Secteur> expertise) throws ErrAge{
        super(nom,prenom,age);
        this.expertise=expertise;
    }

    /**
     * Récupère la liste des expertises
     * @return La liste des objets Secteur associés à cet expert
     */
    public List<Secteur> getExpertise(){
        return expertise;
    }

    /**
     * Propose un nouveau projet basé sur les compétences de l'expert
     * <br>
     * L'expert choisit aléatoirement l'un des secteurs de son expertise pour l'attribuer au nouveau projet créé
     * @param titre Le titre du projet proposé
     * @param description La description du projet
     * @return Un nouvel objet Projet associé à un secteur de l'expert ou null si l'expert n'a aucune expertise
     */
    public Projet projetProposition(String titre,String description){
        if(expertise.isEmpty()){
            return null;
        }
        Random random=new Random();
        int numProjet=random.nextInt(expertise.size());
        Secteur secteur=expertise.get(numProjet);
        return new Projet(titre,description,secteur);
    }

    /**
     * Méthode d'évaluation héritée de la classe abstraite Equipe
     * <br>
     * Expert ne participe pas à l'évaluation des coûts/bénéfices des projets, cette méthode est donc vide.
     * @param projet Le projet concerné.
     */
    @Override
    public void evaluer(Projet projet) {
        System.out.println("Methode vide");
    }
}