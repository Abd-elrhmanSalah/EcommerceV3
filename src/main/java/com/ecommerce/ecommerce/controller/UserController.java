package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.UserLoginRequestDto;
import com.ecommerce.ecommerce.dto.request.UserRequestDto;
import com.ecommerce.ecommerce.dto.response.UserLoginResponseDto;
import com.ecommerce.ecommerce.dto.response.UserResponseDto;
import com.ecommerce.ecommerce.dto.statusResponse.BlockStatus;
import com.ecommerce.ecommerce.service.LoginService;
import com.ecommerce.ecommerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Controller",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH, AND DELETE  AND login User details")
@RestController
@RequestMapping("/api/admin/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    @Operation(summary = "Create a new user", description = "API to create a new user in the system.")
    @PostMapping("/create")
    public UserResponseDto createUser(@RequestBody UserRequestDto userDto) {
        return userService.createUser(userDto);
    }

    @Operation(summary = "delete user by id", description = "API to delete user in the system.")
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @Operation(summary = "update user by id", description = "API to update user in the system.")
    @PutMapping("/update/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserRequestDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @Operation(summary = "show all user", description = "API to show all user in the system.")
    @GetMapping("/list")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "show user by id", description = "API to show user in the system by id.")
    @GetMapping("/show/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "block user by id", description = "API to block user in the system by id.")
    @PatchMapping("/block/{id}")
    public BlockStatus blockUser(@PathVariable Long id) {
        return userService.blockUser(id);
    }

    @Operation(summary = "unblock user by id", description = "API to unblock user in the system by id")
    @PatchMapping("/unblock/{id}")
    public BlockStatus unblockUser(@PathVariable Long id) {
        return userService.unblockUser(id);
    }

    @Operation(summary = "login user in system", description = "API to make user login")
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto userDto) {
        return loginService.login(userDto);
    }

}
