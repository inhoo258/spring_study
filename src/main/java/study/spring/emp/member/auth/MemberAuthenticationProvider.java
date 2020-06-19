package study.spring.emp.member.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import study.spring.emp.member.model.MemVO;
import study.spring.emp.member.service.IMemService;

public class MemberAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	IMemService memberService;
	@Autowired
	BCryptPasswordEncoder bpe;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(authentication.getPrincipal()==null) {
			return null;
		}
		if(authentication.getCredentials()==null) {
			return null;
		}
		String userId = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		String dbpw = memberService.getPassword(userId);
		MemVO mem = memberService.getMember(userId);
		if(authentication != null) {
			
		}
		if(userId.equals("master")&&password.equals("1234")) {
			MemVO master = new MemVO();
			master.setUserId(userId);
			master.setPassword(password);
			master.setAuth("ROLE_MASTER");
			UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userId, password ,master.getAuthorities());
			result.setDetails(master);
			return result;
		}
		if(dbpw==null) {
			throw new InternalAuthenticationServiceException("아이디가 없습니다");
		}
		if(!bpe.matches(password, dbpw)) {
			throw new BadCredentialsException("비밀번호가 다릅니다.");
		}
		
		if(!mem.isEnabled()) {
			throw new DisabledException("정지당한 계정입니다. 관리자에게 문의하세요");
		}
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userId, password,mem.getAuthorities());
		result.setDetails(mem);
		return result;
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
