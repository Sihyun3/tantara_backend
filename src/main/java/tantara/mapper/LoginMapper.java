package tantara.mapper;

import org.apache.ibatis.annotations.Mapper;

import tantara.dto.LoginDto;
import tantara.dto.UserDto;

@Mapper
public interface LoginMapper {
	public UserDto login(LoginDto loginDto) throws Exception;
	public int registUser(UserDto userDto) throws Exception;
	public UserDto selectUserByUserId(String userId) ;
}
