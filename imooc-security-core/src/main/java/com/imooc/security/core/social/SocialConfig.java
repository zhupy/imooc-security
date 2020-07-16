package com.imooc.security.core.social;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.social.qq.config.ImoocSpringSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author zhupeiyou
 * @since 2020-07-10 10:44
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ConnectionSignUp connectionSignUp;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        jdbcUsersConnectionRepository.setTablePrefix("imooc_");
        jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
        return jdbcUsersConnectionRepository;
    }

    @Bean
    public SpringSocialConfigurer imoocSocialSecurityConfig() {
        //return new SpringSocialConfigurer();
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        ImoocSpringSocialConfigurer imoocSpringSocialConfigurer = new ImoocSpringSocialConfigurer(filterProcessesUrl);
        String signUpUrl = securityProperties.getBrowser().getSignUpUrl();
        imoocSpringSocialConfigurer.signupUrl(signUpUrl);
        return imoocSpringSocialConfigurer;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator)){

        };
    }
}
