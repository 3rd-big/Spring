package lifecycle;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("lifecycle/config.xml");
		System.out.println("load 이후");
		
		ctx.refresh();
		System.out.println("refresh 이후");
		
		
		
		BeanLifeCycle3 bean = ctx.getBean(BeanLifeCycle3.class);
		System.out.println("getBean 이후");
		
		ctx.close();
	}
}
