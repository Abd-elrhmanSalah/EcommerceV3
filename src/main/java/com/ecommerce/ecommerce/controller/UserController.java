package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.UserRequestDto;
import com.ecommerce.ecommerce.dto.response.UserResponseDto;
import com.ecommerce.ecommerce.dto.statusResponse.BlockStatus;
import com.ecommerce.ecommerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "User Controller",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH, AND DELETE User details")
@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Create a new user", description = "API to create a new user in the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User successfully created."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PostMapping("/create")
    public UserResponseDto createUser(@RequestBody UserRequestDto userDto){
        return userService.createUser(userDto);
    }

    @Operation(summary = "delete user by id", description = "API to create a new user in the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User successfully created."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }


    @PutMapping("/update/{id}")
    public UserResponseDto updateUser(@PathVariable Long id , @RequestBody UserRequestDto userDto){
        return userService.updateUser(id , userDto);
    }



    @GetMapping("/list")
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping("/show/{id}")
    public UserResponseDto getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }


    @PatchMapping("/block/{id}")
    public BlockStatus blockUser(@PathVariable Long id){
        return userService.blockUser(id);
    }





    @PatchMapping("/unblock/{id}")
    public BlockStatus unblockUser(@PathVariable Long id){
        return userService.unblockUser(id);
    }

}
