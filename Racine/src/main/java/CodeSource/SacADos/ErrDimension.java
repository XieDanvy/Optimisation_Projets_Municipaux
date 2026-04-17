package CodeSource.SacADos;
/**
 * Exception personnalisée signalant une incohérence de dimension
 * <br>
 * Cette exception est levée lorsqu'il y a un décalage entre le nombre de contraintes d'un sac à dos et le nombre de coûts définis pour un objet ou si une dimension est invalide lors de l'initialisation
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class ErrDimension extends Exception{
    /**
     * Construit une nouvelle instance de l'exception avec un message explicatif
     * @param message Le détail de l'erreur
     */
    public ErrDimension(String message){
        super(message);
    }
}