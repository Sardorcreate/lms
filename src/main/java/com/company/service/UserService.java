package com.company.service;

import com.company.dto.UserDto;
import com.company.dto.UserFilterDto;
import com.company.entity.User;
import com.company.exception.AllException;
import com.company.repository.UserFilterRepository;
import com.company.repository.UserRepository;
import com.company.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserFilterRepository userFilterRepository;

//    public String updateRSV(UserDto userDto) {
//
//        Optional<User> byLogin = userRepository.findByLogin(userDto.getLogin());
//
//        if (byLogin.isEmpty()) {
//            throw new AllException("User with this login info not found!!!");
//        }
//
//        User user = byLogin.get();
//        user.setRole(userDto.getRole());
//        user.setStatus(userDto.getStatus());
//        user.setVisible(userDto.getVisible());
//
//        userRepository.save(user);
//
//        return "Successful!!!";
//    }
//
//    public String updateNSP(UserDto userDto) {
//
//        Optional<User> byLogin = userRepository.findByLogin(userDto.getLogin());
//
//        if (byLogin.isEmpty()) {
//            throw new AllException("User with this login info not found!!!");
//        }
//
//        String md5 = MD5Util.getMd5(userDto.getPassword());
//
//        User user = byLogin.get();
//        user.setName(userDto.getName());
//        user.setSurname(userDto.getSurname());
//        user.setPassword(md5);
//
//        userRepository.save(user);
//
//        return "Successful!!!";
//    }

    public String updateRSV(UserDto userDto) {

        Integer i = userRepository.updateRSV(userDto.getRole(),
                                             userDto.getStatus(),
                                             userDto.getVisible(),
                                             userDto.getLogin());

        if (i == 0) {
            throw new AllException("User has not been updated with the given information!!!");
        }

        return "User has been updated successfully!!!";
    }

    public String updateNSP(UserDto userDto) {

        String md5 = MD5Util.getMd5(userDto.getPassword());

        Integer i = userRepository.updateNSP(userDto.getName(),
                                             userDto.getSurname(),
                                             md5,
                                             userDto.getLogin());

        if (i == 0) {
            throw new AllException("User has not been updated with the given information!!!");
        }

        return "User has been updated successfully!!!";
    }

    public List<UserDto> readUsers() {

        List<User> all = userRepository.findAll();

        List<UserDto> userDtos = new ArrayList<>();

        for (User user : all) {

            UserDto userDto = authService.entityToDto(user);

            userDtos.add(userDto);
        }

        return userDtos;
    }

    public String updateI(UserDto userDto) {

        Integer i = userRepository.updateI(userDto.getImagePath(),
                                           userDto.getLogin());

        if (i == 0) {
            throw new AllException("User with this login info has not been updated!!!");
        }

        return "User has been updated succesfully!!!";
    }

    public List<UserDto> getByPagination(Integer page, Integer size) {

        Integer offset = (page - 1) * size;

        List<User> users = userRepository.getUsers(size, offset);

        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            UserDto userDto = authService.entityToDto(user);

            userDtos.add(userDto);
        }

        return userDtos;
    }

    public List<UserDto> getUserList(UserFilterDto userFilterDto) {

        List<User> userList = userFilterRepository.getUserList(userFilterDto);

        List<UserDto> userDtos = new ArrayList<>();

        for (User user : userList) {

            UserDto userDto = authService.entityToDto(user);

            userDtos.add(userDto);
        }

        return userDtos;
    }
}
