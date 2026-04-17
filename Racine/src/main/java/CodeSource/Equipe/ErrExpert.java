package CodeSource.Equipe;

/**
 * Exception personnalisée signalant une erreur concernant les experts de l'équipe
 * <br>
 * Cette exception est levée si la liste des experts fournie est vide
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class ErrExpert extends Exception{
    /**
     * Construit une nouvelle instance de l'exception avec un message explicatif
     * @param message Le détail de l'erreur
     */
    public ErrExpert(String message){
        super(message);
    }
}