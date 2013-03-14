package networking.packages;

public class AuthenticationResponse extends Response{
	public enum AuthenticationResponseType{
		APPROVED, USER_NOEXIST, WRONG_PASS
	}
	
	private AuthenticationResponseType type;

	public AuthenticationResponse(AuthenticationResponseType type) {
		super();
		this.type = type;
	}

	public AuthenticationResponseType getType() {
		return type;
	}
	
	
}
