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

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(auth);
    }

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

                                .requestMatchers(HttpMethod.POST, "/users/{id}/promote").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users/{username}").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.PUT,"/users/{username}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/users/{username}").hasRole("ADMIN")


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
                                .requestMatchers(HttpMethod.DELETE, "stocks/{id}").hasRole("ADMIN")

                                                        //----------products-------//

                                .requestMatchers(HttpMethod.GET, "products").permitAll()
                                .requestMatchers(HttpMethod.POST, "products").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.POST, "products/{id}/*").hasAnyRole("ADMIN","CLIENT")
                                //TODO:moet deze blijven staan? IK HEB NOG NIKS INGEBOUWD VOOR REVIEWS OID
                                .requestMatchers(HttpMethod.PUT, "products/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "products/{id}").hasRole("ADMIN")

                                                        //-----upload & download images----//

                                .requestMatchers(HttpMethod.POST, "/image").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/image/{id}").permitAll()


                                                        //---------orders---------//

                                .requestMatchers(HttpMethod.GET, "/orders/{id}").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.GET, "/orders").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/orders").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.PUT, "/orders/{id}/admin").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "orders/{id}/client").hasRole("CLIENT")
                                .requestMatchers(HttpMethod.DELETE, "/orders/{id}").hasRole("ADMIN")

                                //----------invoices--------//

                                .requestMatchers(HttpMethod.GET, "invoices").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.POST, "invoices").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "invoices/{id}").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.DELETE, "invoices/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/invoices/{id}").hasRole("ADMIN")


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


//TODO: POST ORDER/INVOICE VOOR ADMIN NIET MOGELIJK OM OP TE VRAGEN KRIJG EEN 403