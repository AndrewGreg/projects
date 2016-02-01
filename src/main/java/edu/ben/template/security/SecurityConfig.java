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
		.passwordEncoder(new CustomPasswordEncoder(256));		
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				.authorizeRequests()
				/* TODO Add any additional open links here */
				.antMatchers("/").permitAll().antMatchers("/about").permitAll()
				.antMatchers("/login").permitAll();
				/* TODO Add any additional authorities here... */
//				.antMatchers("/**").hasAnyAuthority("ROLE_USER") 
//				.and()
//					.logout()
//						.deleteCookies("JSESSIONID")
//						.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
//						.logoutSuccessUrl("/")
//					.permitAll()
//				.and()
//					.formLogin()
//					.successHandler(new SimpleUrlAuthenticationSuccessHandler("/"))
//					.loginPage("/login").permitAll()
//					.loginProcessingUrl("/login")
//					.usernameParameter("j_username")
//					.passwordParameter("j_password")
//					.failureUrl("/loginFailure")
//					.permitAll()
//				.and()
//					.rememberMe()
//					.key("eidkaieh")
//					.userDetailsService(userDetailsService());
	}
}