package CodeSource.Equipe;
import java.util.Arrays;
import java.util.List;

/**
 * Représente un secteur dans la simulation municipale
 * <br>
 * Elle garantit que seuls des secteurs valides et pré-définis sont utilisés grâce à une liste statique de référence
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class Secteur{
    /** Le nom du secteur */
    private String nomSecteur;
    /**
     * Liste statique et immuable des secteurs autorisés dans la simulation
     * <br>
     * Les valeurs valides sont: "sport", "santé", "éducation", "culture", "attractivité économique"
     */
    private static final List<String> listeSecteur= Arrays.asList("sport","santé","éducation","culture","attractivité économique");

    /**
     * Construit un nouveau secteur en vérifiant sa validité
     * <br>
     * Le constructeur compare le nom fourni avec la liste des secteurs autorisés, si le nom n'est pas trouvé, une exception est levée pour empêcher la création d'un secteur invalide
     * @param nomSecteur Le nom du secteur à créer
     * @throws ErrSecteur Si le nom fourni ne fait pas partie de la liste des secteurs autorisés
     */
    public Secteur(String nomSecteur) throws ErrSecteur{
        if(!listeSecteur.contains(nomSecteur)){
            throw new ErrSecteur("Veuillez entrer un secteur valable");
        }
        this.nomSecteur=nomSecteur;
    }

    /**
     * Récupère le nom du secteur
     * @return Le nom du secteur sous forme de chaîne de caractères
     */
    public String getNomSecteur(){
        return nomSecteur;
    }
}