package eu.ioannidis.unisys.oauthservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

import javax.sql.DataSource;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    private DataSource dataSource;

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    @Autowired
    public OAuth2Config(AuthenticationManager authenticationManager, @Qualifier("OAuthDetailsService") UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("unisysClient")
                .secret("$2b$10$Y0AqqXlgqlCyaBax3l/sf.MA6wrKwUMhuXJTNA.ZYTzXWg.FIkb3y")
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                .scopes("webclient");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
}
