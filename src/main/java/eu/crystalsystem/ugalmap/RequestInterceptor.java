package eu.crystalsystem.ugalmap;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class RequestInterceptor 
  extends HandlerInterceptorAdapter {

	private Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    @Override
    public boolean preHandle(
      HttpServletRequest request, 
      HttpServletResponse response, 
      Object handler) {
        return true;
    }
 
    @Override
    public void afterCompletion(
      HttpServletRequest request, 
      HttpServletResponse response, 
      Object handler, 
      Exception ex) {
		logger.log(Level.INFO, "\n");
		logger.log(Level.INFO, "REQUEST \n\n  ");
		Enumeration<?> requestHeaders = request.getHeaderNames();
		while (requestHeaders.hasMoreElements()) {
			String key = (String) requestHeaders.nextElement();
			String value = request.getHeader(key);
			logger.log(Level.INFO, "{0}  \t  {1}", new Object[] { key, value });
		}
		logger.log(Level.INFO, "\n");
		logger.log(Level.INFO, "RESPONSE \n");
		Enumeration<?> responseHeaders = request.getHeaderNames();
		while (responseHeaders.hasMoreElements()) {
			String key = (String) responseHeaders.nextElement();
			String value = request.getHeader(key);
			logger.log(Level.INFO, "{0}  \t  {1}", new Object[] { key, value });
		}

	}
}