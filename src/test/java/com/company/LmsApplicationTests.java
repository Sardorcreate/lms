package com.company;

import com.company.dto.PassportDto;
import com.company.dto.UserDto;
import com.company.entity.Passport;
import com.company.enums.Role;
import com.company.enums.Status;
import com.company.repository.JDBCRepository;
import com.company.service.AuthService;
import com.company.service.PassportService;
import com.company.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LmsApplicationTests {

//	@Autowired
//	private AuthService authService;
//	@Autowired
//	private PassportService passportService;
	@Autowired
	private JDBCRepository jdbcRepository;

	@Test
	void contextLoads() {

		Integer select = jdbcRepository.update(2, 3);

		System.out.println(select);

//		PassportDto passportDto = new PassportDto();
//		passportDto.setPassportSerie("AD");
//		passportDto.setPassportNumber(1421587.);
//		passportDto.setJshshir("53105066620031");
//
//		UserDto userDto = new UserDto();
//		userDto.setName("Sardor");
//		userDto.setSurname("Abdusalomov");
//		userDto.setLogin("sardor");
//		userDto.setPassword("sardor3105");
//		userDto.setRole(Role.ROLE_SUPERADMIN);
//		userDto.setStatus(Status.ACTIVE);
//		userDto.setPassportDto(passportDto);
//		userDto.setImagePath("C:\\Users\\Asus\\Pictures");
//		userDto.setVisible(true);
//
//		authService.signup(userDto);

	}

}
