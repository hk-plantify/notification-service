package com.plantify.notification.global.util;

import com.plantify.notification.domain.dto.AuthUserResponse;
import com.plantify.notification.global.exception.ApplicationException;
import com.plantify.notification.global.exception.errorcode.AuthErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserInfoProvider {

    public Mono<AuthUserResponse> getUserInfo() {
        return ReactiveSecurityContextHolder.getContext()
                .map(context -> {
                    Authentication authentication = context.getAuthentication();
                    if (authentication == null || authentication.getPrincipal() == null) {
                        throw new ApplicationException(AuthErrorCode.INVALID_TOKEN);
                    }

                    Long userId = (Long) authentication.getPrincipal();
                    String role = authentication.getAuthorities().stream()
                            .findFirst()
                            .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", ""))
                            .orElse("UNKNOWN");

                    return new AuthUserResponse(userId, role);
                });
    }
}
