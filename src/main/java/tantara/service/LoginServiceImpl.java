package tantara.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tantara.dto.LoginDto;
import tantara.dto.UserDto;
import tantara.mapper.LoginMapper;

@Service 
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;
	//해시값 설정
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDto login(LoginDto loginDto) throws Exception {
		return loginMapper.login(loginDto);
	}
	
	@Override
	public int registUser(UserDto userDto) throws Exception {
		// 패스워드를 암호화 해서 새로 저장
		userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
		return loginMapper.registUser(userDto);
	}
	
	//String username, String password, boolean enabled, 
	//boolean accountNonExpired, boolean credentialsNonExpired, 
	//boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto userDto = loginMapper.selectUserByUserId(username);
		if(userDto == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(userDto.getUserId(),userDto.getUserPassword(),true,true,true,true,new ArrayList<>());
	}
}
