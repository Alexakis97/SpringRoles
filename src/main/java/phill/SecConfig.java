package phill;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecConfig  extends WebSecurityConfigurerAdapter{
	

	
	
	
	protected void configure(HttpSecurity http) throws Exception
	{
		//h hasAnyRole("ADMIN","USER") klp
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasRole("USER")
		.antMatchers("/").permitAll()
		.and().formLogin().defaultSuccessUrl("/redirect")
		.and().logout()
		.permitAll();	

		
	}
	
	@Bean 
	public PasswordEncoder getPasswordEncoder() {return NoOpPasswordEncoder.getInstance();}

	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		
		
		auth.inMemoryAuthentication().withUser("phillalexakis").password("google").roles("USER")
		.and()
		.withUser("nicksterg9").password("google").roles("ADMIN");
	}
}
