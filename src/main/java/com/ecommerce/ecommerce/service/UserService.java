package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.UserRequestDto;
import com.ecommerce.ecommerce.dto.response.UserResponseDto;
import com.ecommerce.ecommerce.dto.statusResponse.BlockStatus;
import com.ecommerce.ecommerce.entity.SystemUser;
import com.ecommerce.ecommerce.repositories.UserRepositories;
import com.ecommerce.ecommerce.utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
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
        SystemUser existingUser = userRepositories.findById(id)
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPhoneNumber(userDto.getPhoneNumber());
        existingUser.setPassword(userDto.getPassword());

        SystemUser saved = userRepositories.save(existingUser);
        return ObjectMapperUtils.map(saved , UserResponseDto.class);
    }


    public void deleteUser(Long id) {
        SystemUser existingUser = userRepositories.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()->new RuntimeException("User Not Found!"));
        existingUser.setIsBlockedUser(true);
        userRepositories.save(existingUser);
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

}