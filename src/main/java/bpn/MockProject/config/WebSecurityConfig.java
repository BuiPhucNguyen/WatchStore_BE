package bpn.MockProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtAuthFilter jwtAuthFilter)
			throws Exception {
		return httpSecurity.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorization -> authorization
						//account
						.requestMatchers("/auths/login").permitAll()
						.requestMatchers("/auths/signup").permitAll()
						.requestMatchers(HttpMethod.GET,"/accounts/current").authenticated()
						.requestMatchers(HttpMethod.GET,"/accounts/**").hasAuthority("ROLE_ADMIN")
						.requestMatchers(HttpMethod.PUT, "/accounts/status/**").hasAuthority("ROLE_ADMIN")
						//brands
						.requestMatchers(HttpMethod.GET,"/brands/all").hasAuthority("ROLE_ADMIN")
						.requestMatchers(HttpMethod.POST, "/brands/").hasAuthority("ROLE_ADMIN")
						.requestMatchers(HttpMethod.PUT, "/brands/**").hasAuthority("ROLE_ADMIN")
						.requestMatchers(HttpMethod.GET,"/brands/**").permitAll()
						//product
						.requestMatchers(HttpMethod.GET, "/products/all").hasAuthority("ROLE_ADMIN")
						.requestMatchers(HttpMethod.POST, "/products/").hasAuthority("ROLE_ADMIN")
						.requestMatchers(HttpMethod.PUT, "/products/**").hasAuthority("ROLE_ADMIN")
						.requestMatchers(HttpMethod.GET, "/products/**").permitAll()
						//order
						.requestMatchers(HttpMethod.GET, "/orders/all").hasAuthority("ROLE_ADMIN")
						.requestMatchers(HttpMethod.GET, "/orders/export").hasAuthority("ROLE_ADMIN")
						.requestMatchers(HttpMethod.PUT, "/orders/**").hasAuthority("ROLE_ADMIN")
						//feedback
						.requestMatchers(HttpMethod.GET, "/feedbacks/all").hasAuthority("ROLE_ADMIN")
						.requestMatchers(HttpMethod.GET, "/feedbacks/**").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/feedbacks/remove/**").hasAuthority("ROLE_ADMIN")
						//other request
						.anyRequest().authenticated())
				.exceptionHandling(handling -> handling.authenticationEntryPoint(authenticationEntryPoint()))
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}
}
