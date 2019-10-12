package disciplinasUFCG;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import disciplinasUFCG.util.Filtro;

@SpringBootApplication
public class Lab02PsoftApplication {
	
	@Bean
	public FilterRegistrationBean<Filtro> filterJwt() {
		FilterRegistrationBean<Filtro> filterRB = new FilterRegistrationBean<Filtro>();
		filterRB.setFilter(new Filtro());
		filterRB.addUrlPatterns("/auth/usuarios", "/disciplinas/likes/*", "/disciplinas/comentarios/*");
		return filterRB;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Lab02PsoftApplication.class, args);
	}

}
