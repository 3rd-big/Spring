package lifecycle;

import javax.security.auth.Destroyable;

import org.springframework.beans.factory.InitializingBean;

public class BeanLifeCycle1 implements InitializingBean{

	public void destroy() throws Exception{
		
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

}
