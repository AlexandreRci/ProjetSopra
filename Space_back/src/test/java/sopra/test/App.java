package sopra.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sopra.config.AppConfig;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		ctx.getBeanFactory().createBean(TestJPA.class).run();
		ctx.close();

	}

}
