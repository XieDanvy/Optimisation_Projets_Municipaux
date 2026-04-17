package CodeSource.SacADos;
/**
 * Exception personnalisée signalant l'utilisation d'une valeur négative
 * <br>
 * Cette exception est levée pour quand un nombre négatif est fourni lors de l'initialisation d'un objet
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class ErrNegatif extends Exception{
    /**
     * Construit une nouvelle instance de l'exception
     * @param message Le détail de l'erreur
     */
    public ErrNegatif(String message){
        super(message);
    }
}