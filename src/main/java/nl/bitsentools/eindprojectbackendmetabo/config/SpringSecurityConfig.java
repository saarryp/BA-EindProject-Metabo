package nl.bitsentools.eindprojectbackendmetabo.config;


import nl.bitsentools.eindprojectbackendmetabo.filter.JwtRequestFilter;
import nl.bitsentools.eindprojectbackendmetabo.services.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    public final CustomUserDetailService customUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    // PasswordEncoderBean. Deze kun je overal in je applicatie injecteren waar nodig.
    // Je kunt dit ook in een aparte configuratie klasse zetten.
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Authenticatie met customUserDetailsService en passwordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(auth);
    }




    // Authorizatie met jwt
    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                        auth
                                // Wanneer je deze uncomments, staat je hele security open. Je hebt dan alleen nog een jwt nodig.
//                .requestMatchers("/**").permitAll()

                                                            //OPEN ENDPOINTS//


                                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()


                                //----------------------------ENDPOINTS ADMIN & CLIENT :  USERNAME EN WACHTWOORD -------------------------------

                                .requestMatchers(HttpMethod.POST, "/users").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.POST, "/users/{id}/promote").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users/{id}").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.PUT,"/users/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/users/{id}").hasRole("ADMIN")

                                //----------------------------ENDPOINTS AUTHENTICATIE---------------------------------

                                .requestMatchers("/authenticated").authenticated()
                                .requestMatchers("/authenticate").permitAll()

                                .requestMatchers(HttpMethod.POST, "/users/{id}/password").authenticated()
                                .requestMatchers(HttpMethod.POST, "/users/{id}").hasRole("CLIENT")
                                .requestMatchers(HttpMethod.DELETE, "/users/{id}").hasRole("CLIENT")

                                                        //-----------stocks--------//

                                .requestMatchers(HttpMethod.GET, "stocks").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST,"stocks").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "stocks/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "stocks/{id").hasRole("ADMIN")

                                                        //----------products-------//

                                .requestMatchers(HttpMethod.GET, "products").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.POST, "products").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "products/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "products/{id}").hasRole("ADMIN")

                                                        //---------orders---------//

                                .requestMatchers(HttpMethod.GET, "orders").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.POST, "orders").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "orders/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "orders/{id}").hasRole("ADMIN")

                                                        //----------invoices--------//

                                .requestMatchers(HttpMethod.GET, "invoices").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.POST, "invoices").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "invoices/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "invoices/{id}").hasRole("ADMIN")

                                                        //----------warranties--------//

                                .requestMatchers(HttpMethod.GET, "warranties").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.POST, "warranties").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "warranties/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "warranties/{id}").hasRole("ADMIN")



                                .anyRequest().denyAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
