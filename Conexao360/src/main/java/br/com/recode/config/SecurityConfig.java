package br.com.recode.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.recode.servico.UsuarioDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UsuarioDetailsService usuarioDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
				.csrf().disable()
				//.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/usuario/**").hasRole("USER")
				//.antMatchers("/page/**","/","/index","/css/**", "/imagens/**", "/logos/**","/login").permitAll()
				.antMatchers("/**").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/login");
				//.and()
				//.httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		log.info("Password encoded{}", passwordEncoder.encode("teste"));
		auth.inMemoryAuthentication()
				.withUser("admin")
				.password(passwordEncoder.encode("admin"))
				.roles("USER", "ADMIN");
		auth.userDetailsService(usuarioDetailsService)
			.passwordEncoder(passwordEncoder);
	}
}
