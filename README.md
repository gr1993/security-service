# Spring Security를 이용한 다양한 인증 방식 구현

## normal 
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


## HTML 출처 (무료 템플릿 사이트)
* https://freefrontend.com/bootstrap-code-examples
* https://bootsnipp.com