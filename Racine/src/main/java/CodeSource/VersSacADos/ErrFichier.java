package CodeSource.VersSacADos;

/**
 * Exception signalant une erreur lors du traitement d'un fichier
 * <br>
 * Cette exception est levée lorsqu'un problème survient lors de la lecture, de l'ouverture ou de l'analyse d'un fichier externe destiné à être converti en sac à dos
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class ErrFichier extends Exception{
    /**
     * Construit une nouvelle instance de l'exception
     * @param message Le détail de l'erreur
     */
    public ErrFichier(String message){
        super(message);
    }
}