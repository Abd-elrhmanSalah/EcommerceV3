package com.ecommerce.ecommerce.service;


import com.ecommerce.ecommerce.dto.request.UserLoginRequestDto;
import com.ecommerce.ecommerce.dto.response.UserLoginResponseDto;
import com.ecommerce.ecommerce.entity.SystemUser;
import com.ecommerce.ecommerce.repositories.UserRepositories;
import com.ecommerce.ecommerce.utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserRepositories userRepositories;

    public UserLoginResponseDto login(UserLoginRequestDto userDto) {

        SystemUser user = userRepositories.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email Not Found!"));

        if (!user.getPassword().equals(userDto.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }
        if (user.getIsBlockedUser().equals(true)) {
            throw new RuntimeException("User Is Blocked");
        }
        return ObjectMapperUtils.map(user, UserLoginResponseDto.class);
    }

}
