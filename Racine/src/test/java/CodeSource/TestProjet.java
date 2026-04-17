package CodeSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import CodeSource.Equipe.Projet;
import CodeSource.Equipe.Secteur;
import CodeSource.Equipe.ErrSecteur;


/**
 * Classe de test JUnit pour valider le comportement de la classe {@link Projet}
 * <br>
 * Cette classe vérifie le cycle de vie basique d'un projet:
 * <br>
 * -L'initialisation correcte via le constructeur
 * <br>
 * -La mise à jour du bénéfice
 * <br>
 * -La mise à jour des coûts
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class TestProjet {

    /**
     * Teste le constructeur de la classe Projet
     * <br>
     * Création d'un nouveau projet avec un titre, une description et un secteur
     * <br>
     * Les attributs titre, description et secteur sont correctement enregistrés
     * <br>
     * Tous les indicateurs chiffrés (bénéfice, coûts) sont initialisés à 0 par défaut
     */
    @Test
    @DisplayName("Test Constructeur: Initialisation par défaut")
    void testConstructeur() throws ErrSecteur{
        String titre="Nouveau Parc";
        String description="Un parc pour les enfants";
        Secteur secteur=new Secteur("attractivité économique"); 
        Projet projet=new Projet(titre, description, secteur);
        Assertions.assertEquals("Nouveau Parc", projet.getTitre());
        Assertions.assertEquals("Un parc pour les enfants", projet.getDescription());
        Assertions.assertEquals(secteur, projet.getSecteur());
        Assertions.assertEquals(0, projet.getBenefice(), "Le bénéfice initial doit être 0");
        Assertions.assertEquals(0, projet.getCoutSocial(), "Le coût social initial doit être 0");
        Assertions.assertEquals(0, projet.getCoutEco(), "Le coût économique initial doit être 0");
        Assertions.assertEquals(0, projet.getCoutEnviron(), "Le coût environnemental initial doit être 0");
    }
    /**
     * Teste la méthode de modification du bénéfice
     * <br>
     * Vérifie que la valeur du bénéfice est bien mise à jour après appel de la méthode
     * <br>
     * Cela simule l'action d'une {@link Elue} qui attribue une note au projet
     */
    @Test
    @DisplayName("Test Modification du Bénéfice")
    void testSetBenefice()throws ErrSecteur{
        Projet projet=new Projet("Test", "Desc", new Secteur("santé"));
        projet.Benefice(150);
        Assertions.assertEquals(150, projet.getBenefice());
    }
    /**
     * Teste les méthodes de modification des différents coûts
     * <br>
     * Vérifie que les setters pour les coûts social, économique et environnemental fonctionnent
     * <br>
     * Cela simule le passage des différents {@link Evaluateur} sur le projet
     */
    @Test
    @DisplayName("Test Modification des Coûts")
    void testSetCouts() throws ErrSecteur{
        Projet projet=new Projet("Test", "Desc", new Secteur("attractivité économique"));
        projet.coutSocial(10);
        projet.coutEco(200);
        projet.coutEnviron(5);
        Assertions.assertEquals(10, projet.getCoutSocial());
        Assertions.assertEquals(200, projet.getCoutEco());
        Assertions.assertEquals(5, projet.getCoutEnviron());
    }
}