package CodeSource.VersSacADos;
/**
 * Exception signalant qu'un projet est invalide ou incomplet pour la conversion
 * <br>
 * Cette exception est levée lors d'un échec de transformation d'un {@code Projet} en {@code Objet}
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class ErrProjet extends Exception{
    /**
     * Construit une nouvelle instance de l'exception
     * @param message Le détail de l'erreur
     */
    public ErrProjet(String message){
        super(message);
    }
}