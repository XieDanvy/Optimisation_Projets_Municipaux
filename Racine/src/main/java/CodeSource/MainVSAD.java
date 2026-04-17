package CodeSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import CodeSource.Equipe.Elue;
import CodeSource.Equipe.ErrAge;
import CodeSource.Equipe.ErrEval;
import CodeSource.Equipe.ErrExpert;
import CodeSource.Equipe.ErrSecteur;
import CodeSource.Equipe.ErrSpe;
import CodeSource.Equipe.Evaluateur;
import CodeSource.Equipe.Expert;
import CodeSource.Equipe.Projet;
import CodeSource.Equipe.Secteur;
import CodeSource.Equipe.effectifEquipe;
import CodeSource.GloutonSolver.ComparatorMax;
import CodeSource.GloutonSolver.ComparatorSomme;
import CodeSource.GloutonSolver.GloutonAjouterSolver;
import CodeSource.GloutonSolver.GloutonRetraitSolver;
import CodeSource.HillClimbing.HillClimbingSolver;
import CodeSource.SacADos.ErrDimension;
import CodeSource.SacADos.ErrNegatif;
import CodeSource.SacADos.Objet;
import CodeSource.SacADos.SacADos;
import CodeSource.VersSacADos.ErrFichier;
import CodeSource.VersSacADos.ErrProjet;
import CodeSource.VersSacADos.VersSacADos;

/**
 * Classe principale d'exécution (Main)
 * <br>
 * Cette classe sert d'interface pour l'utilisateur afin qu'il puisse tester le code.
 * <br>
 * Elle permet de:
 * <br>
 * Générer un sac à dos via une simulation de l'équipe municipale, ou par lecture d'un fichier 
 * <br>
 * Résoudre le problème d'optimisation par application d'algorithmes Gloutons ou Hill Climbing
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class MainVSAD{
    /** Scanner unique pour la lecture des entrées au clavier */
    private static final Scanner scanner=new Scanner(System.in);

    /**
     * Point d'entrée du programme
     * <br>
     * Affiche le menu principal et gère l'interaction avec l'utilisateur
     * <br>
     * Les options disponibles sont:
     * <br>
     * -Simulation avec 3 budgets
     * <br>
     * -Simulation avec 5 budgets
     * <br>
     * -Chargement du sac à dos depuis un fichier externe
     * <br>
     * Une fois le sac à dos créé, la méthode délègue la résolution à {@link #Resolution(SacADos)}.
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        boolean continuer=true;

        while(continuer){
            System.out.println("Choisissez votre option:");
            System.out.println("1. Génération d'un sac à dos avec 3 Budgets: économique, social, et environnemental");
            System.out.println("2. Génération d'un sac à dos avec 5 Budgets: Secteurs");
            System.out.println("3. Lire un fichier");
            System.out.println("4. Quitter");
            System.out.print("Votre choix: ");
            
            int input=lireEntier(1, 4);

            if(input==4){
                continuer=false;
                System.out.println("À la prochaine!");
                break;
            }

            try{
                SacADos sac=null;

                if(input==1){
                    System.out.println("Option: budgets par type de coût");
                    List<Projet> projetsMunicipaux1=simulerEquipeMunicipale();
                    if(projetsMunicipaux1.isEmpty()){
                        throw new ErrProjet("Aucun projet n'a été généré par l'équipe.");
                    }
                    
                    System.out.println("Veuillez entrer les budgets globaux :");
                    int budgetEco=demanderBudget("Économique");
                    int budgetSocio=demanderBudget("Social");
                    int budgetEnviron=demanderBudget("Environnemental");
                    int[] budgets=new int[]{budgetEco, budgetSocio, budgetEnviron};
                    sac=VersSacADos.Projets3Budgets(projetsMunicipaux1, budgets);
                } 
                
                else if(input==2){
                    System.out.println("Option: budgets par secteurs");
                    List<Projet> projetsMunicipaux2=simulerEquipeMunicipale();
                    if(projetsMunicipaux2.isEmpty()) {
                        throw new ErrProjet("Aucun projet n'a été généré par l'équipe.");
                    }
                    System.out.println("Veuillez définir les budgets pour chaque secteur, coût économique uniquement:");
                    int[] budgetsSecteurs=new int[5];
                    String[] nomsSecteurs={"Sport", "Santé", "Éducation", "Culture", "Attractivité Économique"};
                    
                    for (int i=0; i<5; i++){
                        budgetsSecteurs[i]=demanderBudget(nomsSecteurs[i]);
                    }
                    sac=VersSacADos.Projets5Budgets(projetsMunicipaux2, budgetsSecteurs);
                } 
                
                else{
                    System.out.println("Option lecteure de fichier:");
                    System.out.println("Veuillez entrer le chemin du fichier :");
                    String chemin=scanner.nextLine();
                    sac= VersSacADos.readFile(chemin);
                }

                if(sac!=null){
                    Resolution(sac);
                } 
                
                else {
                    System.out.println("Erreur, impossible de créer le Sac à Dos.");
                }

            } 
            
            catch (ErrDimension e) {
                System.out.println("Un problème a été rencontré avec la dimension du sac");
            }

            catch (ErrNegatif e) {
                System.out.println("Un problème a été rencontré avec les valeurs des coûts du sac");
            }

            catch (ErrFichier e) {
                System.out.println("Un problème a été rencontré avec la lecture d'un fichier");
            }

            catch (ErrSecteur e) {
                System.out.println("Un problème a été rencontré avec les secteurs du projet");
            }

            catch (ErrProjet e) {
                System.out.println("Un problème a été rencontré avec les projets de l'equipe municipale");
            }

            catch(Exception e){
                System.out.println("Une erreur est survenue");
                e.printStackTrace();
            }
            
            if(continuer){
                System.out.println("Appuyez sur Entrée pour revenir au menu principal");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
    /**
     * Gère le menu de sélection des algorithmes et lance la résolution du problème
     * <br>
     * Permet à l'utilisateur de choisir entre:
     * <br>
     * Glouton par ajout
     * <br>
     * Glouton par retrait
     * <br>
     * Hill Climbing (Classique, Aléatoire ou Itératif)
     * @param sac L'instance du problème de sac à dos à résoudre
     */
    private static void Resolution(SacADos sac) {
        System.out.println("Quel algorithme souhaitez-vous utiliser ?");
        System.out.println("1. Glouton Ajout avec ComparatorSomme");
        System.out.println("2. Glouton Retrait avec ComparatorMax");
        System.out.println("3. Hill Climbing");
        System.out.println("4. Quitter");
        int choix=lireEntier(1, 4);
        List<Objet> solution=new ArrayList<>();

            if(choix==1){
                GloutonAjouterSolver solverAjoute=new GloutonAjouterSolver(sac);
                solution=solverAjoute.SolverAjout(sac, new ComparatorSomme());
            }
                
            else if(choix==2){
                GloutonRetraitSolver solverRetire=new GloutonRetraitSolver(sac);
                solution=solverRetire.SolverRetrait(sac, new ComparatorMax());
            }

            else if(choix==3){
                System.out.println("Choisissez votre méthode Hill Climbing");
                System.out.println("1. Classique");
                System.out.println("2. Exploration aléatoire");
                System.out.println("3. Exploration itérative");
                System.out.print("Votre choix: ");
                int typeHC=lireEntier(1, 3);
                System.out.print("Nombre maximum d'itérations: ");
                int iteration=lireEntier(1,Integer.MAX_VALUE);
                HillClimbingSolver hc=new HillClimbingSolver(sac, 1, iteration);

                if(typeHC==1){
                    System.out.println("Utilisation du Hill Climbing Classique");
                    solution=hc.solverClassic(new ArrayList<>());
                } 

                else if(typeHC==2){
                    System.out.println("Utilisation du Hill Climbing avec voisinage aléatoire");
                    solution=hc.solverRandom(new ArrayList<>());
                }

                else{
                    System.out.println("Préparation des solutions initiales différentes");
                    List<List<Objet>> pointsDeDepart=new ArrayList<>();
                    
                    pointsDeDepart.add(new ArrayList<>());
                    
                    GloutonAjouterSolver solutionSolver=new GloutonAjouterSolver(sac);
                    List<Objet> solutionAjoute=solutionSolver.SolverAjout(sac, new ComparatorSomme());
                    pointsDeDepart.add(solutionAjoute);
                    
                    if (!sac.getObjets().isEmpty()) {
                    List<Objet> objetRandom=new ArrayList<>();
                    Random rand=new Random();
                    int indexRandom=rand.nextInt(sac.getObjets().size());
                    objetRandom.add(sac.getObjets().get(indexRandom));
                    pointsDeDepart.add(objetRandom);
                    }
                    solution=hc.solverIterative(pointsDeDepart);
                }
            }

            else{
                return;
            }
            Resultats(solution);
    }
    /**
     * Méthode pour demander un budget spécifique à l'utilisateur
     * @param nomBudget Le nom du budget
     * @return La valeur saisie par l'utilisateur
     */
    private static int demanderBudget(String nomBudget){
        System.out.print("Budget pour " + nomBudget + " : ");
        return lireEntier(0, Integer.MAX_VALUE);
    }
    /**
     * Méthode pour lire un entier depuis la console
     * <br>
     * Cette méthode gère les erreurs et vérifie que le nombre est bien compris dans l'intervalle demandé
     * <br>
     * @param min La valeur minimale acceptée
     * @param max La valeur maximale acceptée
     * @return Un entier valide
     */
    private static int lireEntier(int min, int max){
        int valeur=0;
        while(true){
            try{
                String input=scanner.nextLine();
                valeur=Integer.parseInt(input);
                if(valeur>=min && valeur<=max){
                    break;
                }
                else{
                    System.out.print("Nombre invalide, le nombre doit être compris entre " + min + " et " + max + ". Réessayer:");
                }
            } 
            catch(NumberFormatException e){
                System.out.print("Ce n'est pas un nombre valide. Réessayez: ");
            }
        }
        return valeur;
    }
    /**
     * Affiche les résultats finaux de la résolution
     * <br>
     * Présente le nombre de projets retenus, l'utilité totale générée et les coûts totaux
     * @param solution La liste des objets retenus dans la solution finale
     */
    private static void Resultats(List<Objet> solution) {
        System.out.println("Résultat:");
        int utiliteTotale=0;
        int[] coutsTotal=null;
        
        if (!solution.isEmpty()){
            coutsTotal=new int[solution.get(0).getCouts().length];
            for (Objet obj: solution) {
                utiliteTotale+=obj.getUtilite();
                int[] couts=obj.getCouts();
                for(int i=0; i<couts.length; i++) {
                    coutsTotal[i]+=couts[i];
                }
            }
        }

        System.out.println("Nombre de projets sélectionnés: " + solution.size());
        System.out.println("Utilité Totale: " + utiliteTotale);
        System.out.println("Coûts : " + Arrays.toString(coutsTotal));
    }
    /**
     * Configure et lance une simulation de l'équipe municipale
     * <br>
     * Cette méthode crée un test complet avec: des secteurs d'activité, des experts avec domaines d'expertises, des évaluateurs spécialisés et un élue
     * <br>
     * Elle lance ensuite plusieurs cycles de simulation pour générer une liste de projets potentiels
     * @return La liste des projets générés et évalués par l'équipe.
     */
    private static List<Projet> simulerEquipeMunicipale() {
        System.out.println(">> Lancement de la simulation de l'équipe municipale...");
        List<Projet> projetsEvaluer=new ArrayList<>();
        
        try {
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

            effectifEquipe equipe=new effectifEquipe(elue, evaluateurs, experts, new ArrayList<>(),0);
            
            System.out.println("Entrer le nombre de projet voulu (Nb projet= Nb entré*Nb expert):");
            int nbEntre=lireEntier(1, Integer.MAX_VALUE);
            for(int i=0; i<nbEntre; i++){
                equipe.cycleSimulation();
            }
            projetsEvaluer=equipe.getProjetEvaluer();

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
        return projetsEvaluer;
    }
}