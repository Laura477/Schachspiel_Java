//Exception bei Versuch, ins Schach zu ziehen
public class SchachException extends RuntimeException {
	public SchachException(String message) {
		super(message);
	}

}
