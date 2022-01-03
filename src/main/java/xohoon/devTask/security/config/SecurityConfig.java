package xohoon.devTask.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import xohoon.devTask.security.factory.UrlResourcesMapFactoryBean;
import xohoon.devTask.security.filter.PermitAllFilter;
import xohoon.devTask.security.handler.CustomAccessDeniedHandler;
import xohoon.devTask.security.metadata.UrlFilterInvocationSecurityMetadataSource;
import xohoon.devTask.security.provider.CustomAuthenticationProvider;
import xohoon.devTask.security.voter.IpAddressVoter;
import xohoon.devTask.service.SecurityResourcesService;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationDetailsSource authenticationDetailsSource;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private SecurityResourcesService securityResourcesService;
    private String[] permitAllResources = {"/", "/users", "/member/login/**", "/login*", "/error", "admin/*"};

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override // db 연동 커스텀한 구현체로 인증처리
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider()); // 커스텀한 검증 구현체 사용
    }

    @Override
    public void configure(WebSecurity web) throws Exception { // 정적파일 ignore 설정
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_proc")
                .authenticationDetailsSource(authenticationDetailsSource) // 추가인증
                .defaultSuccessUrl("/")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
        ;
        http
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
            .and()
                .addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class)
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() { // 암호화 bean 등록
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        accessDeniedHandler.setErrorPage("/denied");
        return accessDeniedHandler;
    }

    @Bean
    public PermitAllFilter customFilterSecurityInterceptor() throws Exception{
        PermitAllFilter permitAllFilter = new PermitAllFilter(permitAllResources);
        permitAllFilter.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
        permitAllFilter.setAccessDecisionManager(affirmativeBased());
        permitAllFilter.setAuthenticationManager(authenticationManagerBean());
        return permitAllFilter;
    }

    private AccessDecisionManager affirmativeBased() {
        AffirmativeBased affirmativeBased = new AffirmativeBased(getAccessDecisionVoters());
        return affirmativeBased;
    }

    private List<AccessDecisionVoter<?>> getAccessDecisionVoters() {
        // role hierarchy 추가
        List<AccessDecisionVoter<? extends Object>> accessDecisionVoters = new ArrayList<>();
//        accessDecisionVoters.add(new IpAddressVoter(securityResourcesService)); // IP voter 가장 먼저 심사
        accessDecisionVoters.add(roleVoter());
        return accessDecisionVoters;
    }

    @Bean
    public AccessDecisionVoter<? extends Object> roleVoter() {
        RoleHierarchyVoter roleHierarchyVoter = new RoleHierarchyVoter(roleHierarchy());
        return roleHierarchyVoter;
    }

    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        return roleHierarchy;
    }

    @Bean
    public UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource() throws Exception{
        return new UrlFilterInvocationSecurityMetadataSource(urlResourcesMapFactoryBean().getObject(), securityResourcesService);
    }

    private UrlResourcesMapFactoryBean urlResourcesMapFactoryBean() {
        UrlResourcesMapFactoryBean urlResourcesMapFactoryBean = new UrlResourcesMapFactoryBean();
        urlResourcesMapFactoryBean.setSecurityResourceService(securityResourcesService);
        return urlResourcesMapFactoryBean;
    }
}
