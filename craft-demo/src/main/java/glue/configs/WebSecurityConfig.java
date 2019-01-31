package glue.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{


	  /**
	   * Enable authentication with two in-memory users: Jack Frost, Dori.
	   *
	   * Spring Security will provide a default login form 
	   */
	  @Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth)
	  throws Exception {
	    auth
	      // Defines three users with their passwords and roles
	      .inMemoryAuthentication()
	      .withUser("JackFrost").password("JackFrost").roles("USER")
	      .and()
	      .withUser("Dori").password("Dori").roles("USER");
	    
	    return;
	  }
	  
	  @Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/rest/**");
	  }
	  
	  /**
	   * Disable CSRF protection (to simplify this demo) and enable the default
	   * login form.
	   */
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	      // Disable CSRF protection
	      .csrf().disable()
	      // Set default configurations from Spring Security
	      .authorizeRequests()
	        .anyRequest().authenticated()
	        .and()
	      .formLogin()
	        .and()
	      .httpBasic();
	   
	    return;
	  }

	} // class WebSecurityConfig

