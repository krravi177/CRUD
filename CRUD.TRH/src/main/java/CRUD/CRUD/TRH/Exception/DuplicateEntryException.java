package CRUD.CRUD.TRH.Exception;

public class DuplicateEntryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DuplicateEntryException(String message) {
		super(message);
	}
}