package idat.edu.pe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	

	
	@Autowired
	private JWTUserDetailService jWTUserDetailService;
	
	@Autowired
	private JWTTokenFilter jWTTokenFilter;
	
	@Autowired
	private JWTEntryPoint jWTEntryPoint;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		

		auth.userDetailsService(jWTUserDetailService).passwordEncoder(encoder());
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/Cliente/editar").access("hasRole('ADMIN')")
			.antMatchers("/Cliente/guardar").access("hasRole('ADMIN')")
			.antMatchers("/Cliente/Eliminar/*").access("hasRole('ADMIN')")
			.antMatchers("/Cliente/listar/*").permitAll()
			.antMatchers("/Hospital/editar").access("hasRole('ADMIN')")
			.antMatchers("/Hospital/guardar").access("hasRole('ADMIN')")
			.antMatchers("/Hospital/Eliminar/*").access("hasRole('ADMIN')")
			.antMatchers("/Hospital/listar/*").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(jWTEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(jWTTokenFilter, UsernamePasswordAuthenticationFilter.class)
			.csrf().disable();
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	

}
