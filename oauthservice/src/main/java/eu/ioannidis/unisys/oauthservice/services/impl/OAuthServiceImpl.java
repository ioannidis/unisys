package eu.ioannidis.unisys.oauthservice.services.impl;

import eu.ioannidis.unisys.oauthservice.models.PrincipalModel;
import eu.ioannidis.unisys.oauthservice.models.entities.PrincipalEntity;
import eu.ioannidis.unisys.oauthservice.repositories.OAuthRepository;
import eu.ioannidis.unisys.oauthservice.services.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Qualifier("OAuthDetailsService")
public class OAuthServiceImpl implements OAuthService {

    private OAuthRepository oAuthRepository;

    @Autowired
    public OAuthServiceImpl(OAuthRepository oAuthRepository) {
        this.oAuthRepository = oAuthRepository;
    }

    @Override
    public Optional<PrincipalEntity> findById(UUID uuid) {
        return oAuthRepository.findById(uuid);
    }

    @Override
    public Optional<PrincipalEntity> findByEmail(String email) {
        return oAuthRepository.findByEmail(email);
    }

    @Override
    public Optional<PrincipalEntity> findByUsername(String username) {
        return oAuthRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return oAuthRepository.findByEmail(s)
                .map(user -> new PrincipalModel(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.isEnabled(),
                        user.isAccountNonExpired(),
                        user.isCredentialsNonExpired(),
                        user.isAccountNonLocked(),
                        user.getAuthorities()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials."));
    }
}
