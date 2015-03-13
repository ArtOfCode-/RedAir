package uk.co.riversparrow.redair.io;

public class FileFormatException extends Exception {
	private static final long serialVersionUID = 1L;

	public FileFormatException() {
	}

	public FileFormatException(String message) {
		super(message);
	}

	public FileFormatException(Throwable cause) {
		super(cause);
	}

	public FileFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileFormatException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
