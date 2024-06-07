package com.springSecurity.securityDemo.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(c -> c.disable())
				.authorizeHttpRequests(
						(authorize) -> authorize.requestMatchers("/auth/**").permitAll().anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults()).formLogin(form -> form.loginPage("/login").permitAll());

		return httpSecurity.build();
	}

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails userDetails = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
				.build();
		return new InMemoryUserDetailsManager(userDetails);
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
		return new ProviderManager(authenticationProvider);
	}

	@Bean
	public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder,
			UserDetailsService userDetailsService) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
//		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url("jdbc:postgresql://localhost:5432/trello");
		dataSourceBuilder.username("postgres");
		dataSourceBuilder.password("root");
		dataSourceBuilder.driverClassName("org.postgresql.Driver");
		return dataSourceBuilder.build();
	}

}
