package networking.packages;

public class AuthenticationRequest extends NetworkRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5884323590819927170L;
	private String username;
	private String password;
	
	
	public AuthenticationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "AuthenticationRequest [username=" + username + ", password="
				+ password + "]";
	}
	
	
}
