package CodeSource.Equipe;

/**
 * Exception personnalisée signalant une erreur liée à la spécialisation d'un membre
 * <br>
 * Cette exception est levée lorsqu'une spécialisation inconnue ou invalide est fournie lors de la création d'un évaluateur
 * <br>
 * Par exemple, si la spécialisation n'est pas l'une des trois attendues : économique, social ou environnemental
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class ErrSpe extends Exception{
    /**
     * Construit une nouvelle instance de l'exception avec un message explicatif
     * @param message Le détail de l'erreur
     */
    public ErrSpe(String message){
        super(message);
    }
}