package exception;

public class CocheraException extends RuntimeException {
	private static final long serialVersionUID = 1234567890987654321L;

	public CocheraException() {
		super();
	}

	public CocheraException(Throwable error) {
		super(error);
	}

	public CocheraException(String mensaje, Throwable error) {
		super(mensaje, error);
	}
}