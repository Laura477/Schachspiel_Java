//Exception bei Versuch, einen ung�ltigen Zug durchzuf�hren
public class ZugException extends RuntimeException {
	public ZugException(String message) {
		super(message);
	}

}