package com.company.service;

import com.company.dto.LoginDto;
import com.company.dto.PassportDto;
import com.company.dto.UserDto;
import com.company.entity.Passport;
import com.company.entity.User;
import com.company.enums.Role;
import com.company.exception.AllException;
import com.company.repository.UserRepository;
import com.company.util.JWTUtil;
import com.company.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PassportService passportService;
    @Autowired
    private SMSService smsService;

    public UserDto login(LoginDto loginDto) {

        Optional<User> byLogin = userRepository.findByLogin(loginDto.getLogin());

        if (byLogin.isEmpty()) {
            throw new AllException("User with this login info not found");
        }

        User user = byLogin.get();

        String encode = JWTUtil.encode(user.getId(), user.getLogin(), user.getRole());

        String md5 = MD5Util.getMd5(loginDto.getPassword());

        if (!md5.equals(user.getPassword())) {
            throw new AllException("Password is incorrect!!!");
        }

        UserDto userDto = entityToDto(user);
        userDto.setJwt(encode);

        return userDto;
    }

    public UserDto signup(UserDto userDto) {

        Optional<User> byLogin = userRepository.findByLogin(userDto.getLogin());

        String md5 = MD5Util.getMd5(userDto.getPassword());

        if (byLogin.isPresent()) {
            throw new AllException("User with this login info has already registered!!!");
        }

        User user = dtoToEntity(userDto, md5);

        User save = userRepository.save(user);

//        smsService.sendSMS(user.getPhone());

        String encode = JWTUtil.encode(save.getId(), save.getLogin(), save.getRole());

        userDto.setJwt(encode);

        return userDto;
    }

    public UserDto entityToDto(User user) {

        PassportDto passportDto = passportService.entityToDto(user.getPassport());

        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setPassportDto(passportDto);
        userDto.setLogin(user.getLogin());
        userDto.setImagePath(user.getImagePath());
        userDto.setPhone(user.getPhone());

        return userDto;
    }

    public User dtoToEntity(UserDto userDto, String md5) {

        Passport passport = passportService.createPassport(userDto.getPassportDto());

        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setLogin(userDto.getLogin());
        user.setPassword(md5);
        user.setPhone(userDto.getPhone());
        user.setPassport(passport);
        user.setImagePath(userDto.getImagePath());
        user.setRole(Role.ROLE_STUDENT);
        user.setStatus(userDto.getStatus());
        user.setVisible(userDto.getVisible());

        return user;
    }
}
