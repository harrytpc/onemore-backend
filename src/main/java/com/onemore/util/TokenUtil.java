package com.onemore.util;

import java.util.Base64;
import java.util.Map;

import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpHeaders;

public class TokenUtil {

	public static Object getClaim(HttpHeaders headers, String property) {
		
		String accessToken = "";
		
		if(headers.get("Authorization") != null){
			accessToken = headers.get("Authorization").get(0);
		}
		
		String[] jwtTokenValues = accessToken.split("\\.");

		if (jwtTokenValues.length > 1) {
			Base64.Decoder decoder = Base64.getUrlDecoder();
			String value = new String(decoder.decode(jwtTokenValues[1].getBytes()));
			
			org.springframework.boot.json.JsonParser springParser = JsonParserFactory.getJsonParser();
			Map<String, Object> result = springParser.parseMap(value);
			return result.get(property);
		}
		
		return null;		
	}

}
