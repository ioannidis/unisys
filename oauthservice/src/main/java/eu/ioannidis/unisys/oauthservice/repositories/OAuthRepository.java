package eu.ioannidis.unisys.oauthservice.repositories;

import eu.ioannidis.unisys.oauthservice.models.entities.PrincipalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OAuthRepository extends JpaRepository<PrincipalEntity, UUID> {

    @Override
    Optional<PrincipalEntity> findById(UUID uuid);

    Optional<PrincipalEntity> findByEmail(String email);

    Optional<PrincipalEntity> findByUsername(String username);
}
