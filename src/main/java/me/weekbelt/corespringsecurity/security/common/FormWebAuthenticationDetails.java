package me.weekbelt.corespringsecurity.security.common;

import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

@Getter
public class FormWebAuthenticationDetails extends WebAuthenticationDetails {

    private final String secretKey;

    public FormWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.secretKey = request.getParameter("secret_key");
    }
}
