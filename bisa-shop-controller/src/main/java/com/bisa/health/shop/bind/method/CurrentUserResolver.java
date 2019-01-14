/*
package com.bisa.health.shop.bind.method;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import com.bisa.health.client.entity.User;
import com.bisa.health.auth.model.JwtPlayload;
import com.bisa.health.shiro.web.entity.User
import com.bisa.health.shiro.web.bind.CurrentUser;
import com.bisa.health.cache.kit.RedisKit;

*/
/**
 * <p>用于绑定@FormModel的方法参数解析器
 * <p>User: Zhang Kaitao
 * <p>Date: 13-1-12 下午5:01
 * <p>Version: 1.0
 *//*

@Component
public class CurrentUserResolver implements HandlerMethodArgumentResolver {
	
	
	@Autowired
	public  RedisKit redisKit;

    public CurrentUserResolver() {
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    	    	
    	
    	if(!SecurityUtils.getSubject().isAuthenticated()){
    		*/
/*
    		if(JudgeIsMoblie(webRequest)){
    			throw new AppAuthException("Please login!!!");
    		}else{
    			throw new WebAuthException("Please login!!!");
    		}
    		*//*

    		return null;
    	}
    	String username ="";
    	if(SecurityUtils.getSubject().getPrincipal() instanceof JwtPlayload){
    		JwtPlayload   jwtPlayload = (JwtPlayload) SecurityUtils.getSubject().getPrincipal();
    		username=jwtPlayload.getUsername();
    	}else{
    	 	username = (String) SecurityUtils.getSubject().getPrincipal();
    	}
    	
    	
    	if(StringUtils.isEmpty(username)){
    		return null;
    	}
  
		
		User sysUser = (User) redisKit.pull(username+SysUser.SysUserKey);
	
        return sysUser;
    }
    
    
    public  boolean JudgeIsMoblie(NativeWebRequest request) {  
        boolean isMoblie = false;  
        String[] mobileAgents = { "iphone", "android","ipad", "phone", "mobile", "wap", "netfront", "java", "opera mobi",  
                "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",  
                "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",  
                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",  
                "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",  
                "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",  
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",  
                "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",  
                "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",  
                "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",  
                "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",  
                "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",  
                "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",  
                "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",  
                "Googlebot-Mobile" };  
        if (request.getHeader("User-Agent") != null) {  
            String agent=request.getHeader("User-Agent");  
            for (String mobileAgent : mobileAgents) {  
                if (agent.toLowerCase().indexOf(mobileAgent) >= 0&&agent.toLowerCase().indexOf("windows nt")<=0 &&agent.toLowerCase().indexOf("macintosh")<=0) {  
                    isMoblie = true;  
                    break;  
                }  
            }  
        }  
        return isMoblie;  
    }  
    
}
*/
