package ru.itis.studentsgiftery.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itis.studentsgiftery.repositories.AccountsRepository;
import ru.itis.studentsgiftery.security.details.AccountUserDetailsService;
import ru.itis.studentsgiftery.security.filters.TokenAuthenticationFilter;
import ru.itis.studentsgiftery.security.filters.TokenAuthorizationFilter;
import ru.itis.studentsgiftery.security.jwt.JwtProvider;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final AccountUserDetailsService accountUserDetailsService;
    private final AccountsRepository accountsRepository;
    private final JwtProvider jwtProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        TokenAuthenticationFilter tokenAuthenticationFilter =
                new TokenAuthenticationFilter(authenticationManagerBean(), objectMapper, accountsRepository, jwtProvider);
        tokenAuthenticationFilter.setFilterProcessesUrl("/api/students-giftery/signIn/");

        TokenAuthorizationFilter tokenAuthorizationFilter = new TokenAuthorizationFilter(objectMapper, jwtProvider);

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(tokenAuthenticationFilter);
        http.addFilterBefore(tokenAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers("/api/students-giftery/signUp").permitAll()
                .antMatchers("/api/students-giftery/confirm/**").permitAll()
                .antMatchers("/api/students-giftery/signIn/").permitAll()
                .antMatchers("/api/students-giftery/accounts").hasAuthority("ADMIN")
                .antMatchers("/api/students-giftery/accounts/**").hasAnyAuthority("ADMIN", "ORGANIZATION")
                .antMatchers("/api/students-giftery/**").authenticated();
    }
}
