package com.backend.security;

import com.backend.parivesh.CustomCorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
                // dont authenticate this particular request
                .authorizeRequests()
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/user/authenticate", "/user/**",
                        "/home/**", "/kya/**", "/kyc/**", "/kyaGenCode/**", "/ConsultantOrganisation/**", "/sector/**",
                        "/response/**", "/cms/**", "/proponentApplicant/**", "/ec/getForm", "/fc/getForm",
                        "/caf/getCafDetails", "/caf/getCafKML", "/ecpartB/getEcPartB", "/ecPartC/getForm",
                        "/wl/getForm", "/crz/getBasicDetails", "/village/**", "/visitor/**", "/caf/getCafKMLbyCAFId",
                        "/project/getProjectbyID", "/project/get", "/cin/**", "/forestClearanceComponentDetails/list",
                        "/forestClearanceProposedDiversionDetails/list", "/ecpartB/getEcPartBbyEcId",
                        "/fc/addundertaking", "/fc/partb/getBasicDetails", "/fc/getAdditionalInformation",
                        "/fc/formb/**", "/fc/formC/**", "/fc/formc/partb/**", "/scenario/**", "/fc/forme/partb/**",
                        "/fc/formE/**", "/project/**", "/ec/**", "/crzTransfer/**", "/fc/formD/**", "/home/**",
                        "/kya/**", "/kyc/**", "/kyaGenCode/**", "/notification/**", "/ConsultantOrganisation/**",
                        "/sector/**", "/response/**", "/cms/**", "/proponentApplicant/**", "/ec/getForm", "/fc/getForm",
                        "/caf/getCafDetails", "/caf/getCafKML", "/ecpartB/getEcPartB", "/ecPartC/getForm",
                        "/wl/getForm", "/crz/getBasicDetails", "/village/**", "/visitor/**", "/caf/getCafKMLbyCAFId",
                        "/project/getProjectbyID", "/cin/**", "/forestClearanceComponentDetails/list", "/documentdetails/getDocumentDetail/**",
                        "/forestClearanceProposedDiversionDetails/list", "/ecpartB/getEcPartBbyEcId",
                        "/fc/addundertaking", "/fc/formaNodal/partIII/getCheckListDetails", "/fc/getAdditionalInform",
                        "/formAPartIIINodalOfficerController/**", "/trackYourProposal/**", "/formApartIV/**", "/ecForm7/get",
                        "/ecFormV/get", "/applications/getById", "/fc/formA/partIV/getBasicDetails", "/eds/getEDSDetails", "/activity/action/getAll"
                        , "/loginApi/**", "fc/formd/partb/getBasicDetails", "/fcFormD/PartIII/get/**", "/fc/formB/partIV/getBasicDetails", "/fcFactsheet/get", "/fc/formd/part4/getBasicDetails/**",
                        "/proponentApplicant/updateProposalDuration", "/PariveshSWS/generateToken","/edsV2/getQueries/**","/ecForm8/get/**","/amendmentLogs/get/**","/ecForm6/v2/getForm/**","/fc/formd/partb/getBasicDetails",
                        "/forma/partc/getBasicDetails", "/user/searchOrganization/**", "/employee/getDetailsByEmail/**","/employee/add")
                .permitAll().

                // all other requests need to be authenticated
                        anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}