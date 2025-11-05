package com.kh.start.configuration.filter;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.token.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/*
 * 추상클래스
 * 
 * 필드의 공유가 필요할 때, 생성자가 필요할 때, 공통 구현 로직이 많을 때
 * =>
 * 다형성을 적용해야한다.
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	
	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;
	
//	private final List<String> permitPath = Arrays.asList(
//			"/auth/login"
//	);
	
	// 필터의 주요 로직을 구현하는 메서드, 요청이 들어올때마다 호출됨
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//log.info("진짜로 요청이 들어올때마다 요친구가 호출되는지 확인");
		
		String uri = request.getRequestURI();
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		// log.info("요청 어케옴? {}", uri);
		// log.info("헤더에 포함시킨 Authorization : {}", authorization);
		
		if(authorization == null || uri.equals("/auth/login")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		// 토큰 검증
		
		String token = authorization.split(" ")[1];
		
		// log.info("토큰 값 : {}", token);
		// 1. 서버에서 관리하는 시크릿키로 만든게 맞는가?
		// 2. 유효기간이 지나지 않았는가?
		
		try {
			Claims claims = jwtUtil.parseJwt(token);
			String username = claims.getSubject();
			
			//log.info("토큰 소유주의 아이디 값 : {}", username);
			
			CustomUserDetails user = 
					(CustomUserDetails)userDetailsService.loadUserByUsername(username);
			// log.info("DB에서 조회해온 user의 정보 : {}", user);
			// session에 담아야지 ~~~
			UsernamePasswordAuthenticationToken authentication
				= new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			// 세부설정관련 사용자의 IP주소, MAC주소, sessionID 등등을 포함시켜서 셋팅
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			// 요렇게 담아주면 현재 요청이 만료될 때까지 Authentication에 담겨져있는 사용자의 정보를 사용할 수 있음
		} catch(ExpiredJwtException e) {
			log.info("토큰의 유효기간 만료");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write("토큰 만료");
			/*
			 * axios.post(url, body, tokens)
			 *      .then(result => {
			 *      result어쩌고젖쩌고~~~
			 *      }).catch(e => {
			 *      	e  == 토큰 만료
			 *      	axios.post(/auth/refresh, 리프레시토큰값)
			 *      		 .then(result => {
			 *      				새 토큰 저장소에 저장;
			 *      				useEffect의 의존성 요소를 변환시켜서 useEffect를 다시 수행;
			 *      			})
			 *      		 .catch(e => {
			 *      				alert("니 로그인 다시해야됨");
			 *      				useNavi("/login");
			 *      			})
			 *      })
			 */
			return;
		} catch(JwtException e) {
			log.info("서버에서 만들어진 토큰이 아님");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("유효하지 않은 토큰입니다.");
			return;
		}
		
		filterChain.doFilter(request, response);
	}

}
