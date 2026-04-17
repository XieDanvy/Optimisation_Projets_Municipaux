package CodeSource;

import CodeSource.SacADos.Objet;
import CodeSource.GloutonSolver.ComparatorSomme;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
/**
 * Classe de test JUnit pour valider le comportement du comparateur {@link ComparatorSomme}
 * <br>
 * Elle vérifie deux fonctionnalités principales:
 * <br>
 * -Le calcul correct de la somme des coûts
 * <br>
 * -La logique de comparaison entre deux objets basée sur le ratio (Utilité / Somme des coûts)
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class TestComparatorSomme {
    /**
     * Teste la méthode {@code somme()}
     * <br>
     * Vérifie que la méthode calcule correctement la somme totale des entiers d'un tableau
     */
    @Test
    @DisplayName("Test de la méthode somme")
    void testSomme() {
        int[] couts={10, 20, 5};
        double resultat=ComparatorSomme.somme(couts);
        Assertions.assertEquals(35.0, resultat, "La somme de {10, 20, 5} doit être 35");
    }
    /**
     * Teste la méthode {@code somme()} avec des valeurs nulles
     * <br>
     * S'assure que la méthode gère correctement un tableau rempli de zéros
     */
    @Test
    @DisplayName("Test de la méthode somme avec un tableau vide")
    void testSommeZero(){
        int[] couts={0, 0, 0};
        Assertions.assertEquals(0, ComparatorSomme.somme(couts));
    }
    /**
     * Teste la méthode {@code compare()} dans le cas où le premier objet est inférieur au second
     * <br>
     * Obj1 (Ratio 1.0) comparé à Obj2 (Ratio 4.0)
     * <br>
     * Résultat: -1 (car 1.0 < 4.0)
     */
    @Test
    @DisplayName("Test Compare, Obj1 a un ratio plus petit que Obj2")
    void testCompareInferieur(){
        try{
            Objet obj1=new Objet(10, new int[]{5, 5});
            Objet obj2=new Objet(40, new int[]{5, 5});
            ComparatorSomme comparator=new ComparatorSomme();
            int resultat=comparator.compare(obj1, obj2);
            Assertions.assertEquals(-1, resultat, "Ratio 1.0 est < 4.0, doit renvoyer -1");
        } 
        catch (Exception e){
            Assertions.fail("Erreur lors de la création des objets");
        }
    }
    /**
     * Teste la méthode {@code compare()} dans le cas où le premier objet est supérieur au second
     * <br>
     * Obj1 (Ratio 4.0) comparé à Obj2 (Ratio 1.0)
     * <br>
     * Résultat: 1 (car 4.0 > 1.0)
     */
    @Test
    @DisplayName("Test Compare, Obj1 a un ratio plus grand que Obj2")
    void testCompareSuperieur() {
        try{
            Objet obj1 = new Objet(40, new int[]{5, 5});
            Objet obj2 = new Objet(10, new int[]{5, 5});
            ComparatorSomme comparator = new ComparatorSomme();
            int resultat = comparator.compare(obj1, obj2);
            Assertions.assertEquals(1, resultat, "Ratio 4.0 est > 1.0, doit renvoyer 1");
        } 
        catch (Exception e){
            Assertions.fail("Erreur lors de la création des objets");
        }
    }
    /**
     * Teste la méthode {@code compare()} dans le cas où les ratios sont identiques
     * <br>
     * Obj1 (Ratio 1.0) comparé à Obj2 (Ratio 1.0)
     * <br>
     * Résultat: 0 (égalité)
     */
    @Test
    @DisplayName("Test Compare, Ratios Égaux")
    void testCompareEgal() {
        try{
            Objet obj1=new Objet(10, new int[]{5, 5});
            Objet obj2=new Objet(20, new int[]{10, 10});
            ComparatorSomme comparator=new ComparatorSomme();
            int resultat=comparator.compare(obj1, obj2);
            Assertions.assertEquals(0, resultat, "Ratios égaux (1.0), doit renvoyer 0");
        } 
        catch (Exception e){
            Assertions.fail("Erreur lors de la création des objets");
        }
    }
}