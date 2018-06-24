package org.firstvision.restapi.constants;

public interface JwtConstant {
	
	long TOKEN_VALIDITY_SECONDS	=	7*24*60*60;
	String SIGNING_KEY			=	"VF@123";
	String TOKEN_PREFIX			=	"Bearer";
	String HEADER_STRING		=	"Authorization";

}
