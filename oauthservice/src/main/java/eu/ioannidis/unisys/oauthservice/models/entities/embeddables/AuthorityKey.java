package eu.ioannidis.unisys.oauthservice.models.entities.embeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class AuthorityKey implements Serializable {

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "authority")
    private String authority;

    public AuthorityKey() {
    }

    public AuthorityKey(UUID userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
