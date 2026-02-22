package com.example.edditcghomespring.authentication.presentation.controller;

import com.example.edditcghomespring.account.application.AccountProfileUseCase;
import com.example.edditcghomespring.authentication.application.SocialAuthenticationUseCase;
import com.example.edditcghomespring.authentication.application.response.KakaoAccessTokenResponse;
import com.example.edditcghomespring.authentication.application.response.KakaoUserInfoResult;
import com.example.edditcghomespring.authentication.application.response.OAuthLinkResult;
import com.example.edditcghomespring.authentication.application.response.SocialLoginResult;
import com.example.edditcghomespring.authentication.domain.vo.SocialProviderType;
import com.example.edditcghomespring.authentication.presentation.dto.request.OAuthLinkRequestForm;
import com.example.edditcghomespring.authentication.presentation.dto.response.OAuthLinkResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class SocialAuthenticationController {

    private final SocialAuthenticationUseCase authenticationUseCase;
    private final AccountProfileUseCase accountProfileUseCase;

    @PostMapping("/link")
    public ResponseEntity<OAuthLinkResponseForm> requestOAuthLink(
            @RequestBody OAuthLinkRequestForm form
    ) {
        OAuthLinkResult result =
                authenticationUseCase.generateOAuthLink(form.toGenerateOAuthLinkCommand());

        return ResponseEntity.ok(
                OAuthLinkResponseForm.from(result)
        );
    }

    @GetMapping("/request-access-token-after-redirection")
    public ResponseEntity<SocialLoginResult> requestAccessToken(
            @RequestParam String code
    ) {
        SocialLoginResult result =
                authenticationUseCase.loginAfterRedirect(
                        SocialProviderType.KAKAO,
                        code
                );

        return ResponseEntity.ok(result);
    }

}
