package com.example.edditcghomespring.authentication.application;

import com.example.edditcghomespring.authentication.application.request.GenerateOAuthLinkCommand;
import com.example.edditcghomespring.authentication.application.response.KakaoAccessTokenResponse;
import com.example.edditcghomespring.authentication.application.response.KakaoUserInfoResult;
import com.example.edditcghomespring.authentication.application.response.OAuthLinkResult;
import com.example.edditcghomespring.authentication.application.response.SocialLoginResult;
import com.example.edditcghomespring.authentication.domain.vo.SocialProviderType;

import java.util.Map;

public interface SocialAuthenticationUseCase {

    OAuthLinkResult generateOAuthLink(GenerateOAuthLinkCommand command);
    KakaoAccessTokenResponse requestKakaoAccessToken(String code);
    KakaoUserInfoResult requestKakaoUserInfo(String accessToken);
    SocialLoginResult issueTemporaryToken(
            String email,
            String nickname,
            SocialProviderType loginType,
            String socialAccessToken
    );
    SocialLoginResult loginAfterRedirect(
            SocialProviderType providerType,
            String code
    );
}
