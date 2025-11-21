package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.UserRequestDto;
import com.ecommerce.ecommerce.dto.response.UserResponseDto;
import com.ecommerce.ecommerce.dto.statusResponse.BlockStatus;
import com.ecommerce.ecommerce.entity.SystemUser;
import com.ecommerce.ecommerce.repositories.UserRepositories;
import com.ecommerce.ecommerce.utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepositories userRepositories;

    public UserResponseDto createUser(UserRequestDto userDto) {
        if (userRepositories.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        SystemUser mappedUser = ObjectMapperUtils.map(userDto, SystemUser.class);
        SystemUser systemUser = userRepositories.save(mappedUser);

        return ObjectMapperUtils.map(systemUser, UserResponseDto.class);
    }


    public List<UserResponseDto> getAllUsers() {

        return ObjectMapperUtils.mapAll(userRepositories.findAll(), UserResponseDto.class);
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userDto) {
        isUserExist(id);

        SystemUser systemUser = ObjectMapperUtils.map(userDto, SystemUser.class);

        SystemUser saved = userRepositories.save(systemUser);
        return ObjectMapperUtils.map(saved, UserResponseDto.class);

    }


    public void deleteUser(Long id) {
        isUserExist(id);
        userRepositories.deleteById(id);
    }


    public UserResponseDto getUserById(Long id) {
        isUserExist(id);

        SystemUser systemUser = userRepositories.findById(id).get();
        return ObjectMapperUtils.map(systemUser, UserResponseDto.class);
    }


    public BlockStatus blockUser(Long id) {
        isUserExist(id);
        SystemUser systemUser = userRepositories.findById(id).get();
        systemUser.setIsBlockedUser(true);
        userRepositories.save(systemUser);
        return new BlockStatus("User blocked successfully", true);
    }


    public BlockStatus unblockUser(Long id) {

        isUserExist(id);

        SystemUser systemUser = userRepositories.findById(id).get();
        systemUser.setIsBlockedUser(false);

        userRepositories.save(systemUser);
        return new BlockStatus("User unblocked successfully", true);
    }

    private void isUserExist(Long userId) {

        Optional<SystemUser> userById = userRepositories.findById(userId);

        if (userById.isEmpty()) {
            throw new RuntimeException("User Not Found");
        }


    }

    public void login(String email, String password) {
        boolean existsByEmailAndPassword = userRepositories.existsByEmailAndPassword(email, password);
        if (!existsByEmailAndPassword) {
            throw new RuntimeException("Username or password incorrect");

        }
    }

}