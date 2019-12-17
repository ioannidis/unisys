package eu.ioannidis.unisys.oauthservice.models.entities;

import eu.ioannidis.unisys.oauthservice.models.entities.embeddables.AuthorityKey;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class AuthorityEntity implements GrantedAuthority {

    @EmbeddedId
    private AuthorityKey id;

    public AuthorityEntity() {
    }

    public AuthorityEntity(AuthorityKey id) {
        this.id = id;
    }

    public AuthorityKey getId() {
        return id;
    }

    @Override
    public String getAuthority() {
        return id.getAuthority();
    }
}
