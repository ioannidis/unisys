package eu.ioannidis.unisys.oauthservice.config.security;

import eu.ioannidis.unisys.oauthservice.models.PrincipalModel;
import eu.ioannidis.unisys.oauthservice.models.entities.PrincipalEntity;
import eu.ioannidis.unisys.oauthservice.services.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

@Component
public class OAuth2Provider implements AuthenticationProvider {

    private OAuthService oAuthService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public OAuth2Provider(OAuthService oAuthService, PasswordEncoder passwordEncoder) {
        this.oAuthService = oAuthService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        PrincipalModel principal = (PrincipalModel) oAuthService.loadUserByUsername(authentication.getName());

        if (!principal.isEnabled()) {
            throw new DisabledException("Account is disabled");
        }

        if (!principal.isAccountNonExpired()) {
            throw new DisabledException("Account has been expired");
        }

        if (!principal.isAccountNonLocked()) {
            throw new DisabledException("Account is locked");
        }

        if (!principal.isCredentialsNonExpired()) {
            throw new DisabledException("Credentials have been expired");
        }

        if (!passwordEncoder.matches(authentication.getCredentials().toString(), principal.getPassword())) {
            throw OAuth2Exception.create("invalid_grant", "Bad credentials");
        }

        return new UsernamePasswordAuthenticationToken(principal,null, principal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
