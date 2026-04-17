package CodeSource.VersSacADos;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CodeSource.Equipe.ErrSecteur;
import CodeSource.Equipe.Projet;
import CodeSource.Equipe.Secteur;
import CodeSource.SacADos.ErrDimension;
import CodeSource.SacADos.ErrNegatif;
import CodeSource.SacADos.Objet;
import CodeSource.SacADos.SacADos;
/**
 * Classe utilitaire de conversion et de chargement pour le problème du sac à dos
 * <br>
 * Cette classe permet de créer des instances de {@link SacADos} à partir de sources différentes:
 * <br>
 * -Soit à partir d'une liste de {@link Projet}
 * <br>
 * -Soit à partir d'un fichier texte
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class VersSacADos{
    /** Liste des secteurs prédéfinis pour la méthode à 5 budgets */
    private static final List<String> listeSecteur=Arrays.asList("sport","santé","éducation","culture","attractivité économique");
    /**
     * Convertit une liste de projets en sac à dos à 3 dimensions
     * <br>
     * Les 3 dimensions correspondent aux types de coûts: coût économique, coût social, coût environnemental
     * @param projets La liste des projets municipaux à convertir en objets
     * @param budgets Un tableau de 3 entiers représentant les budgets pour l'économie, le social et l'environnement
     * @return Une instance de {@code SacADos}
     * @throws ErrDimension Si le tableau des budgets n'a pas une taille de 3
     * @throws ErrNegatif Si un coût détecté dans un projet est négatif
     */
    public static SacADos Projets3Budgets(List<Projet> projets, int[] budgets) throws ErrDimension,ErrNegatif{
        if(budgets.length!=3){
            throw new ErrDimension("Le sac à dos nécessite 3 budgets");
        }
        List<Objet> objets=new ArrayList<>();
        for(Projet projet: projets){
            int utilite=projet.getBenefice();
            int[] couts=new int[budgets.length];
                couts[0]=projet.getCoutEco();
                couts[1]=projet.getCoutSocial();
                couts[2]=projet.getCoutEnviron();

            for(int cout: couts){
                if(cout<0){
                    throw new ErrNegatif("Un coût ne peut pas être négatif");
                }
            }
            Objet objet=new Objet(utilite, couts);
            objets.add(objet);
        }
        return new SacADos(3, budgets, objets);
    }
    /**
     * Convertit une liste de projets en un sac à dos à 5 dimensions
     * <br>
     * Les 5 dimensions correspondent aux secteurs d'activité
     * <br>
     * Chaque projet ne consomme du budget que dans la dimension correspondant à son secteur
     * <br>
     * Seul le coût économique ({@code getCoutEco}) est pris en compte et assigné à la colonne du secteur concerné, les autres coûts sont laissés à 0
     * @param projets La liste des projets municipaux
     * @param budgets Un tableau de 5 entiers représentant les budgets pour chaque secteur
     * @return Une instance de {@code SacADos}
     * @throws ErrDimension Si le tableau des budgets n'a pas une taille de 5
     * @throws ErrSecteur Si un projet appartient à un secteur inconnu de la liste prédéfinie
     * @throws ErrNegatif Si les coûts sont invalides
     */
    public static SacADos Projets5Budgets(List<Projet> projets, int[] budgets) throws ErrDimension,ErrSecteur,ErrNegatif{
        if(budgets.length!=5){
            throw new ErrDimension("Le sac à dos nécessite 5 budgets");
        }
        List<Objet> objets = new ArrayList<>();
        
        for(Projet projet: projets){
            int utilite=projet.getBenefice();
            int[] couts=new int[5];
            Secteur secteur=projet.getSecteur();
            String nomSecteur=secteur.getNomSecteur();
            int indexSecteur=listeSecteur.indexOf(nomSecteur);
            
            if(indexSecteur==-1){
                throw new ErrSecteur("Le nom du secteur est non répertorié");
            }
            couts[indexSecteur]=projet.getCoutEco();
            Objet objet=new Objet(utilite, couts);
            objets.add(objet);
        }
        return new SacADos(5, budgets, objets);
    }
    /**
     * Crée une instance de sac à dos en lisant un fichier de données
     * <br>
     * Le fichier est représenté par une suite de nombres séparés par des espaces et des sauts de ligne
     * @param fichier Le chemin d'accès au fichier texte.
     * @return L'objet {@code SacADos} construit à partir des données du fichier
     * @throws ErrFichier Si le fichier est illisible, vide, trop court ou incomplet
     * @throws ErrNegatif Si des valeurs négatives sont trouvées lors de la création des objets
     * @throws ErrDimension Si la structure des données est incohérente.
     */
    public static SacADos readFile(String fichier) throws ErrFichier,ErrNegatif,ErrDimension {
        List<String> toutesLignes;
        try{
            toutesLignes=Files.readAllLines(Paths.get(fichier));
        }
        catch(java.io.IOException e){
            throw new ErrFichier("Impossible de lire le fichier ");
        }
        
        if(toutesLignes.isEmpty()){
            throw new ErrFichier("Fichier vide");
        }
        
        List<Integer> tousNombres=new ArrayList<>();
        for(String l: toutesLignes){
            if(!l.trim().isEmpty()){
                String[] parties=l.trim().split("\\s+");
                for(String partie: parties){
                    tousNombres.add(Integer.parseInt(partie));
                }
            }
        }
        
        if(tousNombres.size()<3){
            throw new ErrFichier("Fichier trop court");
        }
        
        int n=tousNombres.get(0);
        int k=tousNombres.get(1);
        int index=3;
        
        int totalAttendu=3+n+(k*n)+k;
        if(tousNombres.size()<totalAttendu){
            throw new ErrFichier("Fichier incomplet");
        }
        
        int[] utilites=new int[n];
        for (int i=0; i<n; i++) {
            utilites[i]=tousNombres.get(index);
            index++;
        }
        
        int[][] coutsParBudget=new int[k][n];
        for(int budgetIdx=0; budgetIdx<k; budgetIdx++){
            for(int objetIdx=0; objetIdx<n; objetIdx++){
                coutsParBudget[budgetIdx][objetIdx]=tousNombres.get(index);
                index++;
            }
        }
        
        int[] budgets=new int[k];
        for(int budgetIdx=0; budgetIdx<k; budgetIdx++){
            budgets[budgetIdx]=tousNombres.get(index);
            index++;
        }
        
        List<Objet> objets=new ArrayList<>();
        for (int objetIdx=0; objetIdx<n; objetIdx++) {
            int[] couts=new int[k];
            for (int budgetIdx=0; budgetIdx<k; budgetIdx++) {
                couts[budgetIdx]=coutsParBudget[budgetIdx][objetIdx];
            }
            Objet objet=new Objet(utilites[objetIdx], couts);
            objets.add(objet);
        }
        return new SacADos(k, budgets, objets);
    }
}