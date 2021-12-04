package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
             http
                .authorizeRequests()
                .antMatchers("/design", "/orders")
                    //  .hasRole("ROLE_USER")
                     .access("hasRole('ROLE_USER')")
                .antMatchers("/", "/**")
                .permitAll()
                     .and()
                     .formLogin()
                     .loginPage("/login")
                    // .defaultSuccessUrl("/design",true);//по умолчанию после авторизации.
                                                // Флаг true говорит что надо перебросить
                     .and()
                     .logout()
                     .logoutSuccessUrl("/")
                     .and()
                     .csrf()
                     .ignoringAntMatchers("/h2-console/**")
                     // Allow pages to be loaded in frames from the same origin; needed for H2-Console
                     .and()
                     .headers()
                     .frameOptions()
                     .sameOrigin()
             ;
    }

  /*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication() //just for developing purpose
            *//*    .withUser("buzz")
                .password("infinity")
                .authorities("ROLE_USER")
                .and()*//*
                .withUser("1111")
                .password("1111")
                .authorities("ROLE_USER");
    }*/

  /*  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from Users where username = ?")
                .authoritiesByUsernameQuery(
                        "select username, authority from UserAuthorities where username = ?")
                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));
    }*/
}
