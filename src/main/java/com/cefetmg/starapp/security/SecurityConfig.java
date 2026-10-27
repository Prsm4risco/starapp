package com.cefetmg.starapp.security; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse; 
 
@Configuration 
@EnableWebSecurity 
public class SecurityConfig { 
 
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
		.cors(cors -> Customizer.withDefaults())

		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

		.authorizeHttpRequests(auth -> auth
				
			// Swagger
		    .requestMatchers(
		        "/v3/api-docs/**",
		        "/swagger-ui/**",
		        "/swagger-ui.html"
		    ).permitAll()
                
			// Autenticação
			.requestMatchers(HttpMethod.POST, "/usuarios/login").permitAll()
                
			// Autenticação
			.requestMatchers(HttpMethod.POST, "/usuarios/logout").permitAll()
			
			// Cadastro de usuário
			.requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
			
			// Verificação de login
			.requestMatchers(HttpMethod.GET, "/usuarios/existe").permitAll()

			// Dados do usuário
			.requestMatchers(HttpMethod.GET, "/usuarios/**").hasAnyAuthority("MOD", "USER")

			// Alteração de senha
			.requestMatchers(HttpMethod.PATCH, "/usuarios/**").hasAnyAuthority("MOD", "USER")

			// Consulta de equipes
			.requestMatchers(HttpMethod.POST, "/Postagens").hasAnyAuthority("MOD")

			// Consulta de equipes
			.requestMatchers(HttpMethod.POST, "/comentarios").hasAnyAuthority("MOD", "USER")

			// MOD
			.requestMatchers("/usuarios/**").hasAuthority("MOD")
			.requestMatchers("/Postagens/**").hasAuthority("MOD")
			.requestMatchers("/comentarios/**").hasAuthority("MOD")

			// USER
			.requestMatchers("/Postagens/**").hasAuthority("USER")
			.requestMatchers("/comentarios/**").hasAuthority("USER")

			// Demais recursos
			.requestMatchers("/galeria").permitAll()
			.requestMatchers("/galeria/**").permitAll()
			.anyRequest().authenticated())

			.exceptionHandling(exception -> exception
				// Usuário não autenticado
				.authenticationEntryPoint((request, response, authException) -> {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				})

				// Usuário autenticado, mas sem permissão
				.accessDeniedHandler((request, response, accessDeniedException) -> {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				})
			 )

			.addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
	         );
		
		return http.build();
	} 
     
    @Bean 
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    } 
     
    @Bean 
    public AuthenticationManager authenticationManager( 
            AuthenticationConfiguration authenticationConfiguration) throws Exception { 
 
        return authenticationConfiguration.getAuthenticationManager(); 
    }     
}