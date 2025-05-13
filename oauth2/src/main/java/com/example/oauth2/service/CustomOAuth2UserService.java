package com.example.oauth2.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

//    @Autowired
//    private UserRepository userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 기본 사용자 정보 로드
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // OAuth2 제공자 확인 (kakao, google, naver 등)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 각 공급자별로 사용자 정보 추출 방식 다름
        String email = null;
        String name = null;

        if ("google".equals(registrationId)) {
            email = (String) attributes.get("email");
            name = (String) attributes.get("name");
        } else if ("naver".equals(registrationId)) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            email = (String) response.get("email");
            name = (String) response.get("name");
        } else if ("kakao".equals(registrationId)) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            email = (String) kakaoAccount.get("email");
            name = (String) profile.get("nickname");
        }

        // 이 위치에 회원가입 등을 처리하면 된다.

        return oAuth2User;
    }
}
