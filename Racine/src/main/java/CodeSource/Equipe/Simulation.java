package CodeSource.Equipe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe principale de test et de démonstration
 * <br>
 * Cette classe contient la méthode {@code main} qui simule le processus de création de projet complet:
 * <br>
 * -Création des secteurs d'activité
 * <br>
 * -Création des experts et définition de leurs compétences
 * <br>
 * -Création des évaluateurs
 * <br>
 * -Création de l'élue
 * <br>
 * -Assemblage de l'équipe municipale
 * <br>
 * -Lancement d'un cycle de simulation pour générer des projets
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class Simulation {
    /**
     * Point d'entrée du programme de test
     * <br>
     * Instancie tous les objets nécessaires et lance la méthode {@code cycleSimulation} pour vérifier le bon fonctionnement de la génération et de l'évaluation des projets
     * <br>
     * Cette méthode gère également toutes les exceptions potentielles liées à la création de l'équipe
     * @param arg Arguments de la ligne de commande
     */
    public static void main(String[] arg){
        try{
            Secteur s1=new Secteur("sport");
            Secteur s2=new Secteur("santé");
            Secteur s3=new Secteur("éducation");
            Secteur s4=new Secteur("culture");
            Secteur s5=new Secteur("attractivité économique");

            List<Secteur> exp1=Arrays.asList(s1, s2, s3);
            List<Secteur> exp2=Arrays.asList(s3, s4, s5);

            Expert expert1=new Expert("Aboubakar", "Momo", 40, exp1);
            Expert expert2=new Expert("Wuhan", "Zifang", 18, exp2);
            List<Expert> experts=Arrays.asList(expert1, expert2);

            Evaluateur eval1=new Evaluateur("Zinedine", "Claire", 35, "économique");
            Evaluateur eval2=new Evaluateur("Alalalala", "David", 38, "environnemental");
            Evaluateur eval3=new Evaluateur("Boum", "Reze", 20, "social");
            List<Evaluateur> evaluateurs=Arrays.asList(eval1, eval2, eval3);

            Elue elue=new Elue("Dandadan", "Vy", 25);

            effectifEquipe equipe = new effectifEquipe(elue, evaluateurs, experts, new ArrayList<>(),0);
            equipe.cycleSimulation();
        }

        catch(ErrSecteur e){
            System.out.println("Secteur invalide");
        } 

        catch(ErrSpe e){
            System.out.println("Erreur de spécialisation");
        } 

        catch(ErrAge e){
            System.out.println("Age invalide");
        } 

        catch(ErrEval e){
            System.out.println("Erreur dans le nombre d’évaluateurs");
        } 

        catch(ErrExpert e){
            System.out.println("Erreur dans la liste d’experts");
        } 

        catch(Exception e){
            System.out.println("Une erreur est survenue");
            e.printStackTrace();
        }
    }
}