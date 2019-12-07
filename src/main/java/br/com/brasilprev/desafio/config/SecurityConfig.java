package br.com.brasilprev.desafio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Configuracao de Seguranca da App */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private static final String[] AUTH_WHITELIST = {
                                    "/swagger-resources/**",
                                    "/swagger-ui.html",
                                    "/v2/api-docs",
                                    "/webjars/**",
                                    "/h2/**"
                                };

    //==========================================================================
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        
        //************************* SEM SEGURANCA ******************************
//        http.csrf().disable()
//                
//                    .headers()
//                    .frameOptions().sameOrigin()
//                    .httpStrictTransportSecurity().disable()
//                
//                .and()
//                
//                .authorizeRequests().anyRequest().permitAll();        
        //**********************************************************************
        
        http
            .csrf().disable()
                
            .headers().frameOptions().sameOrigin()
            .httpStrictTransportSecurity().disable()
            .and()
                
            .authorizeRequests()
                
            .antMatchers(Config.PATH_API + "/**").hasRole("USER")
                
            //==================================================================            
            .anyRequest().authenticated()
          
            //===================== BASIC AUTHENTICATION =======================
            .and()
            .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
      
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        
        auth.inMemoryAuthentication()
            .withUser("admin").password(encoder.encode("admin")).roles("ADMIN", "USER")
            .and()
            .withUser("user").password(encoder.encode("123456")).roles("USER");
    }
}
