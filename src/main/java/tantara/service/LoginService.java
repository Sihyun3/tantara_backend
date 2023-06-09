package tantara.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import tantara.dto.LoginDto;
import tantara.dto.UserDto;

public interface LoginService extends UserDetailsService {
	public UserDto login(LoginDto loginDto) throws Exception;
	public int registUser(UserDto userDto) throws Exception;
}
