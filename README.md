# 프로젝트 구조

security-service/
├── normal/         # 일반 로그인(세션 기반 또는 JWT 기반 인증)
├── oauth2/         # Ouath2 기반 소셜 로그인  


# 일반 로그인
일반 로그인 양식으로 2가지 인증 방식을 필요에 따라 지원 가능

### 지원 인증 방식
* 세션 방식 인증 구현
* JWT 방식 인증 구현

### 지원 기능
* 로그인
	* 일반 사용자는 로그인 이후 바로 인증처리
	* 관리자는 로그인 이후 2차 OTP 인증 필요
* 회원가입
* 로그아웃

### 사용 기술
* Bootstrap 5
* JQuery 3.7.1
* Spring Security 6.4.2
* JPA
* mysql connector

### 시연 영상 : [유튜브로 이동](https://youtu.be/-2XIdglsYlg)
![login image](./video/normal-video.gif)


# Ouath2 기반 소셜 로그인
OAuth2 기반의 소셜 로그인 기능을 제공(Oauth2 클라이언트)
인증 이후 JWT 토큰 발급 및 서비스 이용 가능, 로그아웃 기능을 제공하는 간단한 프로젝트

### OAuth2 인증 제공자
* 네이버
* 카카오

### 사용 기술
* Spring Security 6.4.2


## HTML 출처 (무료 템플릿 사이트)
* https://freefrontend.com/bootstrap-code-examples
* https://bootsnipp.com