package br.com.zup.propostacartao.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {

	public static Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> headersInfo = new HashMap<>();
        
        String ip = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR"))
        					.orElse(request.getRemoteAddr());

        if(ip.equals("0:0:0:0:0:0:0:1")) {
        	ip = "127.0.0.1";
        }
        
        headersInfo.put("IP", ip);

        String userAgent = request.getHeader("User-Agent");
        headersInfo.put("USER-AGENT", userAgent);

        return headersInfo;
    }
	
}
