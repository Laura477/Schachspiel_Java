//Exception bei Versuch, einen ungültigen Zug durchzuführen
public class ZugException extends RuntimeException {
	public ZugException(String message) {
		super(message);
	}

}