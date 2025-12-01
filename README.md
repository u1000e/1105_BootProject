# 프로젝트 제목 (세미프로젝트명)

> 한 줄 소개: 프로젝트 진행 전 수업용 코드입니다. - 기본적인 CRUD & REST API 구현

![메인 화면](./images/main.png)

## 프로젝트 소개

이 프로젝트는 **수업용 코드**로, 수강생들의 학습 지원이 목적입니다.
Security FilterChain, JWT 인증, REST API를 통한 CRUD를 구현했습니다.

**개발 기간(예시)**: 2025.11.06 ~ 2024.12.10 (5주)  
**개발 인원**: 5명 

---

## 주요 기능

### 1. 회원 관리
- 회원가입 / 로그인 (JWT 인증)
- 회원 정보 수정 / 탈퇴

### 2. 게시글 관리
- 게시글 등록 / 수정 / 삭제
- 이미지 업로드 
- 페이징 처리

### 3. 댓글 기능
- 댓글 작성
- 댓글 삭제

### 4. 유효값 검증 및 예외처리
- Spring Validation
- GlobalExceptionHandler

---

## 기술 스택

### Frontend
![React](https://img.shields.io/badge/React-18.2.0-61DAFB?logo=react)
![Vite](https://img.shields.io/badge/Vite-5.0.0-646CFF?logo=vite)
![StyledComponents](https://img.shields.io/badge/StyledComponents-3.4.0-06B6D4?logo=tailwindcss)
![Axios](https://img.shields.io/badge/Axios-1.6.0-5A29E4?logo=axios)

### Backend
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.7-6DB33F?logo=springboot)
![Java](https://img.shields.io/badge/Java-21-007396?logo=openjdk)
![Oracle](https://img.shields.io/badge/Oracle-18.0-4479A1?logo=mysql)

### Infra
![Nginx](https://img.shields.io/badge/Nginx-1.25.0-009639?logo=nginx)

---

## 프로젝트 구조

### Backend (Spring Boot)
src/main/java/com/example/project/

├── controller/ <br/>
│ ├── MemberController.java <br/>
│ ├── BoardController.java <br/>
│ └── CommentController.java <br/>
├── service/  <br/>
│ ├── MemberService.java <br/>
│ └── BoardService.java <br/>
├── repository/  <br/>
│ ├── MemberMapper.java <br/>
│ └── BoardMapper.java <br/>
...

## 실행방법

git clone https://github.com/식별자/repo경로.git
cd 프로젝트명

cd backend
./gradlew bootRun
# 또는
java -jar build/libs/project-0.0.1-SNAPSHOT.jar

## 주요 트러블 슈팅

1. JWT 토큰 만료 처리

문제점 : AccessToken 만료 시, 자동 로그인이 아닌 강제 로그아웃

해결방법 : 
JwtFilter항목에서 팀원들과 상의 후 사용자 정의 ResponseCode를 응답하게 만든 뒤, Front단에서 만료코드가 올 시 RefreshToken을 발급받도록 재요청하였음

참고 : https://www.naver.com/어디어디를참고했음~


.....

## 배운점

- Session 방식의 로그인과 JWT인증방식에 대한 차이점 이해
- RestAPI 구현 시 코드 작성법에 대한 이해
- Front-End와 Back-End간의 통신 방법에 대한 이해
- Git브랜치 전략(Git Flow)
- 주 1회 이상의 코드리뷰를 통한 품질 향상
- 매일 스크럼회의를 통한 진행상황 공유


## 향후 개선 사항

- 테스트 코드 작성
- QA테스트
- 리팩토링으로 코드 품질 향상
- 머시기 머시기 기능 확장

## 팀원

  이름        역할               Github
- 홍길동      Member서비스       sdfsdfsdfsd
- 짱구        Board서비스        sdifsodjfiosd
- 고길동

## 문의
### 프로젝트에 대한 문의사항이 있다면 남겨주세요.
- Email : khking@kh.com
- 시연영상 : https://youtube.com/머시기머시기
- 기술블로그 : https://sdfisdfos.kh 

