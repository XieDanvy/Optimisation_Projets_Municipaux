package CodeSource.Equipe;

/**
 * Exception personnalisée signalant une erreur liée à l'âge d'un membre de l'équipe
 * <br>
 * Cette exception est levée lorsque l'âge fourni est invalide, quand elle est négatif par exemple
 * @author Xie Danvy
 * @author Megueddem Shirine
 */
public class ErrAge extends Exception{
    /**
     * Construit une nouvelle instance de l'exception avec un message détaillé
     * @param message Le message expliquant la cause de l'erreur
     */
    public ErrAge(String message){
        super(message);
    }
}