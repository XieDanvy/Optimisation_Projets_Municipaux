package CodeSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import CodeSource.Equipe.Elue;
import CodeSource.Equipe.Projet;
import CodeSource.Equipe.ErrAge;
import CodeSource.Equipe.Secteur;
import CodeSource.Equipe.ErrSecteur;
/**
 * Classe de test JUnit pour valider le comportement de la classe {@link Elue}
 * <br>
 * Cette classe vérifie principalement la méthode d'évaluation des projets par un élu
 * <br>
 * Comme l'évaluation est basée sur l'aléatoire, on vérifie que les résultats restent dans les bornes définies
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class ElueTest{
    /**
     * Teste la méthode {@code evaluer()} de l'Élue
     * <br>
     * La méthode {@code evaluer} doit attribuer un bénéfice au projet
     * <br>
     * Ce bénéfice est généré aléatoirement par la classe {@link Elue}.
     * <br>
     * Le bénéfice ne doit pas être négatif
     * <br>
     * Le bénéfice doit être strictement inférieur à 200 (limite fixée dans le code)
     */
    @Test
    @DisplayName("Test de la méthode evaluer")
    public void testEvaluer() throws ErrSecteur{
        Elue elue=null;
        try{
            elue = new Elue("Macron", "Emmanuel", 45);
        } 
        catch (ErrAge e){
            Assertions.fail("La création de l'élue a échoué : " + e.getMessage());
        }
        Projet projet = new Projet("Nouveau Stade", "vvzezf", new Secteur("santé")); 
        elue.evaluer(projet);
        int resultat=projet.getBenefice(); 
        Assertions.assertTrue(resultat>=0, "Le bénéfice ne doit pas être négatif");
        Assertions.assertTrue(resultat<200, "Le bénéfice doit être strictement inférieur à 200");
        System.out.println("Test réussi, bénéfice attribué = " + resultat);
    }
}