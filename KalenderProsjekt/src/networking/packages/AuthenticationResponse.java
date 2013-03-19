package networking.packages;

public class AuthenticationResponse extends Response{
	private static final long serialVersionUID = 1L;

	public enum AuthenticationResponseType{
		APPROVED, USER_NOEXIST, WRONG_PASS
	}
	
	private AuthenticationResponseType type;

	public AuthenticationResponse(AuthenticationResponseType type) {
		super(ResponseType.AUTHENTICATION_RESPONSE);
		this.type = type;
	}

	public AuthenticationResponseType getType() {
		return type;
	}
	
	
}
