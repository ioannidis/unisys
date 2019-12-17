package eu.ioannidis.unisys.oauthservice.services;

import eu.ioannidis.unisys.oauthservice.models.entities.PrincipalEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.UUID;

public interface OAuthService extends UserDetailsService {
    Optional<PrincipalEntity> findById(UUID uuid);

    Optional<PrincipalEntity> findByEmail(String email);

    Optional<PrincipalEntity> findByUsername(String username);
}
