package edu.ben.template.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import edu.ben.template.dao.DaoConfig;
//import edu.ben.template.dao.DaoConfig;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 
@Import({ DaoConfig.class })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Unsecured resources...
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/favicon.ico").antMatchers("/content/**");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService())
		.passwordEncoder(new StandardPasswordEncoder());		
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean(name="passwordEncoder")
	public PasswordEncoder passwordEncoder(){
		
		return new StandardPasswordEncoder();
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				.authorizeRequests().antMatchers("/").permitAll().antMatchers("/register").permitAll()
				.antMatchers("/jobs").permitAll()
				.antMatchers("/eventsTemplate").permitAll().antMatchers("/faculty").permitAll()
				/* TODO Add any additional open links here */
				.anyRequest().authenticated()
				.and()
					.logout()
						.deleteCookies("JSESSIONID")
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
						.logoutSuccessUrl("/")
					.permitAll()
				.and()
					.formLogin()
					.successHandler(new SimpleUrlAuthenticationSuccessHandler("/"))
					.loginPage("/").permitAll()
					.loginProcessingUrl("//login")
					.usernameParameter("email")
					.passwordParameter("password")
					.failureUrl("/loginFailure")
					.permitAll()
				.and()
					.rememberMe()
					.key("eidkaieh")
					.userDetailsService(userDetailsService());

	}
}