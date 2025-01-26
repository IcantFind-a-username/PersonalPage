package com.myself.franz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myself.franz.DTO.LoginDTO;
import com.myself.franz.DTO.RegisterDTO;
import com.myself.franz.DTO.UserDTO;
import com.myself.franz.entity.User;

public interface UserService extends IService<User> {
    UserDTO login(LoginDTO loginDTO);
    UserDTO register(RegisterDTO registerDTO);
    UserDTO findByEmail(String email);
    boolean updateUserInfo(Long userId, UserDTO userDTO);
    boolean changePassword(Long userId, String oldPassword, String newPassword);
}
