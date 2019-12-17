package eu.ioannidis.unisys.oauthservice.controllers;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(OAuth2Controller.BASE_URL)
public class OAuth2Controller {

    final static String BASE_URL = "/oauth";

    @GetMapping("/user")
    public Map<String, Object> getPrincipal(OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> principal = new HashMap<>();

        principal.put("user", oAuth2Authentication.getUserAuthentication().getPrincipal());
        principal.put("authorities", AuthorityUtils.authorityListToSet(
                oAuth2Authentication.getUserAuthentication().getAuthorities()
        ));

        return principal;
    }
}
