package exception;

public class ClienteExistenteException extends Exception {
    public ClienteExistenteException(String mensagem) {
		super(mensagem);
	}
}
