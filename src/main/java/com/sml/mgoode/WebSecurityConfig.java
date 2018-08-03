package com.sml.mgoode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;

/**
 * Created by michaelgoode on 26/01/2018.
 */
@EnableGlobalMethodSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {




    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher( new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");

        http.csrf().disable();

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource(contextSource())
                .passwordCompare()
                .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute("userPassword");


//////////////////////////////////////////////////////////////////////////////////////////////////////////

        // following section connects to Active Directory
//        String userDNPattern = "";
//
//        ActiveDirectoryLdapAuthenticationProvider adProvider =
//                new ActiveDirectoryLdapAuthenticationProvider("sml.com","ldap://192.168.2.10:389");
//        adProvider.setConvertSubErrorCodesToExceptions(true);
//        adProvider.setUseAuthenticationRequestCredentials(true);
//
//        // set pattern if it exists
//        // The following example would authenticate a user if they were a member
//        // of the ServiceAccounts group
//        // (&(objectClass=user)(userPrincipalName={0})
//        //   (memberof=CN=ServiceAccounts,OU=alfresco,DC=mycompany,DC=com))
//        if (userDNPattern != null && userDNPattern.trim().length() > 0)
//        {
//            adProvider.setSearchFilter(userDNPattern);
//        }
//        auth.authenticationProvider(adProvider);
//
//        // don't erase credentials if you plan to get them later
//        // (e.g using them for another web service call)
//        auth.eraseCredentials(false);
//        // end of section



    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8389/"), "dc=springframework,dc=org");
    }

}
