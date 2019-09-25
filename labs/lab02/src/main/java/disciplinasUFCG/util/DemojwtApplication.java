package disciplinasUFCG.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemojwtApplication {

	@Bean
	public FilterRegistrationBean<Filtro> filterJwt() {
		FilterRegistrationBean<Filtro> filterRB = new FilterRegistrationBean<Filtro>();
		filterRB.setFilter(new Filtro());
		filterRB.addUrlPatterns("/usuarios", "/auth/usuarios");
		return filterRB;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemojwtApplication.class, args);
	}

}

