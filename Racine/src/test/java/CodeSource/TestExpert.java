package CodeSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CodeSource.Equipe.Expert;
import CodeSource.Equipe.Secteur;
import CodeSource.Equipe.Projet;
/**
 * Classe de test JUnit pour valider le comportement de la classe {@link Expert}
 * <br>
 * Cette classe se concentre sur la capacité de l'expert à proposer des projets cohérents:
 * <br>
 * -Vérification qu'un expert propose bien un projet lié à ses compétences
 * <br>
 * -Vérification du cas limite où un expert sans compétence ne peut rien proposer
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class TestExpert {
    /**
     * Teste la méthode {@code projetProposition()} dans un cas standard
     * <br>
     * Dans le cas utilisé, un expert possède deux domaines de compétence (Santé, Éducation)
     * <br>
     * L'objet Projet retourné ne doit pas être null
     * <br>
     * Le secteur du projet généré doit obligatoirement faire partie de la liste des compétences de l'expert
     */
    @Test
    @DisplayName("Test Proposition de Projet")
    public void testPropositionValide(){
        try{
            Secteur sante=new Secteur("santé");
            Secteur education=new Secteur("éducation");
            List<Secteur> competences=Arrays.asList(sante, education);
            Expert expert=new Expert("Curie", "Marie", 50, competences);
            Projet nouveauProjet=expert.projetProposition("Vaccin", "Nouveau traitement");
            Assertions.assertNotNull(nouveauProjet, "L'expert aurait dû créer un objet Projet");
            Secteur secteurChoisi=nouveauProjet.getSecteur();
            Assertions.assertTrue(competences.contains(secteurChoisi), "Le secteur du projet doit faire partie des compétences de l'expert");

        } 
        catch (Exception e){
            Assertions.fail("Erreur inattendue : " + e.getMessage());
        }
    }
    /**
     * Teste la méthode {@code projetProposition()} quand l'expert n'a aucune expertise
     * <br>
     * Création d'un expert avec une liste de compétences vide
     * <br>
     * Résultat: La méthode doit retourner {@code null}, car l'expert ne peut pas choisir de secteur
     */
    @Test
    @DisplayName("Test Expert sans expertise")
    public void testPropositionSansExpertise() {
        try{
            List<Secteur> vide=new ArrayList<>();
            Expert stagiaire=new Expert("Débutant", "Paul", 20, vide);
            Projet projet = stagiaire.projetProposition("Projet Impossible", "Description");
            Assertions.assertNull(projet, "Si la liste d'expertise est vide, la méthode doit retourner null");
        } 
        catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }
}