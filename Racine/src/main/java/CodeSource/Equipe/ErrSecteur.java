package CodeSource.Equipe;

/**
 * Exception personnalisée signalant une erreur liée au secteur d'activité d'un projet ou d'une expertise
 * <br>
 * Cette exception est levée lorsqu'un secteur inconnu est rencontré 
 * <br>
 * Secteur prédéfinie : Sport, Santé, Éducation, Culture, Attractivité économique
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class ErrSecteur extends Exception{
    /**
     * Construit une nouvelle instance de l'exception avec un message explicatif.
     * @param message Le détail de l'erreur
     */
    public ErrSecteur(String message){
        super(message);
    }
}