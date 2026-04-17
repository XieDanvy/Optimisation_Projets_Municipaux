package CodeSource.Equipe;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant l'équipe municipale charger de la gestion des projets
 * <br>
 * Elle regroupe l'élue, les experts, et les évaluateurs
 * <br>
 * Elle gère également le cycle de simulation de proposition et d'évaluation des projets
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class effectifEquipe{
    /** L'élue responsable de la validation finale des projets */
    private final Elue elue;
    /** Liste des évaluateurs chargés d'estimer les coûts des projets */
    private List<Evaluateur> evaluateur;
    /** Liste des experts chargés de proposer des projets */
    private List<Expert> expert;
    /** Liste accumulant tous les projets qui ont été évalués par l'équipe */
    private List<Projet> projetEvaluer;
    /** Compteur interne pour générer des identifiants uniques pour les projets */
    private int compteurProjet=0;

    /**
     * Construit une nouvelle équipe municipale et vérifie les contraintes d'effectif d'equipe
     * @param elue L'élue à la tête de l'équipe
     * @param evaluateur La liste des évaluateurs
     * @param expert La liste des experts
     * @param projetEvaluer La liste destinée à recevoir les projets évalués
     * @param compteurProjet Le numéro de départ pour le compteur de projets
     * @throws ErrEval Si le nombre d'évaluateurs est inférieur à 3.
     * @throws ErrExpert Si la liste d'experts est vide.
     */
    public effectifEquipe(Elue elue,List<Evaluateur> evaluateur,List<Expert> expert,List<Projet> projetEvaluer, int compteurProjet) throws ErrEval,ErrExpert{
        this.elue=elue;
        if(evaluateur.size()<3){
            throw new ErrEval("L'effectif nécessite au moins 3 evaluateurs");
        }
        this.evaluateur=evaluateur;
        if(expert.isEmpty()){
            throw new ErrExpert("L'effectif à besoin d'au moins un expert");
        }
        this.expert=expert;
        this.projetEvaluer=new ArrayList<>();
        this.compteurProjet=compteurProjet;
    }

    /**
     * Récupère l'élue de l'équipe
     * @return L'elue associé à cette équipe
     */
    public Elue getElue(){
        return elue;
    }

    /**
     * Récupère la liste des évaluateurs
     * @return Une liste d'évaluateur
     */
    public List<Evaluateur> getEvaluateur(){
        return evaluateur;
    }

    /**
     * Récupère la liste des experts
     * @return Une liste d'expert
     */
    public List<Expert> getExpert(){
        return expert;
    }

    /**
     * Récupère la liste des projets qui ont été évalués jusqu'à présent
     * @return Une liste de projet
     */
    public List<Projet> getProjetEvaluer(){
        return projetEvaluer;
    }

    /**
     * Récupère la valeur actuelle du compteur de projets
     * @return Le nombre total de projets initiés
     */
    public int getCompteurProjet(){
        return compteurProjet;
    }

    /**
     * Lance un cycle complet de simulation
     * <br>
     * Le déroulement est le suivant:
     * <br>
     * -Chaque expert propose un projet
     * <br>
     * -Tous les projets valident, sont transmis aux évaluateurs et élue
     * <br>
     * -Tous les évaluateurs et l'élue évaluent le projet, ajoutant les coûts et bénéfices
     * <br>
     * -Le projet est ajouté à la liste finale 'projetEvaluer'
     */
    public void cycleSimulation(){
        List<Projet> projetAnalyse=new ArrayList<>();
        for(Expert ex: expert ){
            String titre="Titre"+compteurProjet;
            String description="Description"+compteurProjet;
            Projet projet=ex.projetProposition(titre,description);
            compteurProjet++;
            if(projet!=null){
                projetAnalyse.add(projet);
            }
        }
        for(Projet projet: projetAnalyse){
            for(Evaluateur eval: evaluateur){
                eval.evaluer(projet);
            }
            elue.evaluer(projet);
            projetEvaluer.add(projet);
            System.out.println(projet.getTitre()+" a été ajouté comme projet");
        }
    }
}