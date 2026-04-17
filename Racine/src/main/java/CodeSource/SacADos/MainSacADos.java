package CodeSource.SacADos;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe principale de test basique pour le package {@code SacADos}
 * <br>
 * Cette classe a pour but de valider la modélisation des données du sac à dos. Elle vérifie que l'instanciation des objets s'effectue correctement
 * @author Xie Danvy
 * @author Megueddem Shirine
 * @version 1.0
 */
public class MainSacADos{
    /**
     * Point d'entrée du test
     * <br>
     * Les étapes sont les suivantes:
     * <br>
     * -Création de 4 objets avec des utilités et des coûts sur 3 dimensions
     * <br>
     * -Regroupement de ces objets dans une liste
     * <br>
     * -Mise en place des budgets à respecter pour les 3 dimensions
     * <br>
     * -Création de l'objet {@code SacADos}
     * <br>
     * -Affichage de confirmation si aucune exception n'est levée
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args){
        try {
            Objet obj1=new Objet(10, new int[]{3, 2, 4});
            Objet obj2=new Objet(20, new int[]{6, 4, 2});
            Objet obj3=new Objet(15, new int[]{5, 3, 1});
            Objet obj4=new Objet(8,  new int[]{2, 1, 5});

            List<Objet> objets = new ArrayList<>();
            objets.add(obj1);
            objets.add(obj2);
            objets.add(obj3);
            objets.add(obj4);

            int[] budgets={10, 7, 9};
            SacADos sac=new SacADos(3, budgets, objets);
            System.out.println("Le sac avec les paramètres "+sac+" a été crée");
        }

        catch(ErrNegatif e){
            System.out.println("Une variable est négatif, ce qui est impossbile");
        }

        catch(ErrDimension e){
            System.out.println("La dimension du sac ou des objets est incorrecte");
        }
         
        catch(Exception e) {
            System.out.println("Un erreur est suvenue");
            e.printStackTrace();
        }
    }
}