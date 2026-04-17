package CodeSource;

import CodeSource.SacADos.Objet;
import CodeSource.GloutonSolver.ComparatorMax;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
/**
 * Classe de test JUnit pour valider le comportement du comparateur {@link ComparatorMax}
 * <br>
 * Elle vérifie deux fonctionnalités principales:
 * <br>
 * -Le calcul correct de la valeur maximale dans un tableau d'entiers
 * <br>
 * -La logique de comparaison entre deux objets basée sur le ratio (Utilité / Coût Max)
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class TestComparatorMax{
    /**
     * Teste la méthode {@code max()}
     * <br>
     * Vérifie que la méthode identifie correctement le plus grand entier dans un tableau
     */
    @Test
    @DisplayName("Test de la méthode max")
    void testMax(){
        int[] couts={10, 5, 50, 2};
        int resultat=ComparatorMax.max(couts);
        Assertions.assertEquals(50, resultat, "La méthode max devrait retourner la plus grande valeur (50)");
    }
    /**
     * Teste la méthode {@code compare()} dans le cas où le premier objet est inférieur au second
     * <br>
     * Obj1 (Ratio 1.0) comparé à Obj2 (Ratio 2.0)
     * <br>
     * Résultat: -1 (car 1.0 < 2.0)
     */
    @Test
    @DisplayName("Test Compare: Ratio Inférieur")
    void testCompareInferieur() {
        try{
            Objet obj1=new Objet(10, new int[]{10});
            Objet obj2=new Objet(20, new int[]{10});
            ComparatorMax comparator=new ComparatorMax();
            int resultat=comparator.compare(obj1, obj2);
            Assertions.assertEquals(-1, resultat, "1 est inférieur à 2, le résultat devrait être -1");
        }
        catch (Exception e){
            Assertions.fail("Erreur lors de la création des objets");
        }
    }
    /**
     * Teste la méthode {@code compare()} dans le cas où le premier objet est supérieur au second
     * <br>
     * Obj1 (Ratio 3.0) comparé à Obj2 (Ratio 2.0)
     * <br>
     * Résultat: 1 (car 3.0 > 2.0)
     */
    @Test
    @DisplayName("Test Compare: Ratio Supérieur")
    void testCompareSuperieur() {
        try{
            Objet obj1=new Objet(30, new int[]{10});
            Objet obj2=new Objet(20, new int[]{10});
            ComparatorMax comparator=new ComparatorMax();
            int resultat=comparator.compare(obj1, obj2);
            Assertions.assertEquals(1, resultat, "3 est supérieur à 2, le résultat devrait être 1");
        }
        catch (Exception e){
            Assertions.fail("Erreur lors de la création des objets");
        }
    }
    /**
     * Teste la méthode {@code compare()} dans le cas où les ratios sont identiques
     * <br>
     * Obj1 (Ratio 1.0) à comparé Obj2 (Ratio 1.0)
     * <br>
     * Résultat: 0 (égalité)
     */
    @Test
    @DisplayName("Test Compare: Ratios Égaux")
    void testCompareEgal() {
        try {
            Objet obj1 = new Objet(10, new int[]{10});
            Objet obj2 = new Objet(20, new int[]{20});
            ComparatorMax comparator = new ComparatorMax();
            int resultat = comparator.compare(obj1, obj2);
            Assertions.assertEquals(0, resultat, "Les ratios sont égaux (1.0), le résultat devrait être 0");
        }
        catch (Exception e){
            Assertions.fail("Erreur lors de la création des objets");
        }
    }
}