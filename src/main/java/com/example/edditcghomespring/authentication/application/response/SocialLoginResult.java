package com.example.edditcghomespring.authentication.application.response;

import com.example.edditcghomespring.authentication.domain.vo.SocialProviderType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SocialLoginResult {
    private final boolean isNewUser;
    private final String email;
    private final String nickname;
    private final SocialProviderType loginType;
    private final String temporaryToken;
}
