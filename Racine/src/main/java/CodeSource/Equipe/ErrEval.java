package CodeSource.Equipe;

/**
 * Exception personnalisée signalant une erreur concernant les évaluateurs de l'équipe
 * <br>
 * Cette exception est levée si le nombre d'évaluateurs fournis ne respecte pas le minimum requis
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class ErrEval extends Exception{
    /**
     * Construit une nouvelle instance de l'exception avec un message explicatif
     * @param message Le détail de l'erreur
     */
    public ErrEval(String message){
        super(message);
    }
}