package networking.packages;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable{

	private static final long serialVersionUID = 6025666019258737870L;

	public enum ResponseType{
		QUERY_RESPONSE, AUTHENTICATION_RESPONSE,
	}
	private ResponseType responseType;

	
	public Response(ResponseType responseType) {
		super();
		this.responseType = responseType;
	}


	public ResponseType getResponseType() {
		return responseType;
	}
	
	
}
