package com.plantify.notification.client;

import com.plantify.notification.domain.dto.AuthUserResponse;
import com.plantify.notification.global.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "${auth.service.url}")
public interface AuthServiceClient {

    @PostMapping("/v1/auth/validate-token")
    ApiResponse<AuthUserResponse> getUserInfo(@RequestHeader("Authorization") String authorizationHeader);
}