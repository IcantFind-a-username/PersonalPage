package com.myself.franz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myself.franz.Converter.BeanConverter;
import com.myself.franz.DTO.LoginDTO;
import com.myself.franz.DTO.RegisterDTO;
import com.myself.franz.DTO.UserDTO;
import com.myself.franz.mapper.UserMapper;
import com.myself.franz.entity.User;
import com.myself.franz.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO login(LoginDTO loginDTO){
        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getEmail, loginDTO.getEmail()));
        if(user == null || !passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
            throw new RuntimeException("wrong email address or password!");
        }
        return BeanConverter.toUserDTO(user);
    }

    @Override
    @Transactional
    public UserDTO register(RegisterDTO registerDTO){
        if(exists(registerDTO.getEmail())){
            throw new RuntimeException("email already been registered!");
        }
        User user = new User();
        BeanUtils.copyProperties(registerDTO, user);
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        return BeanConverter.toUserDTO(user);
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getEmail,email));
        if(user == null){
            throw new RuntimeException("user does not exist!");
        }
        return BeanConverter.toUserDTO(user);
    }

    @Override
    @Transactional
    public boolean updateUserInfo(Long userId, UserDTO userDTO) {
        User user = getById(userId);
        if(user == null){
            throw new RuntimeException("user does not exist!");
        }
        //后续实现改邮箱功能
        user.setUsername(userDTO.getUsername());
        return updateById(user);
    }

    @Override
    @Transactional
    public boolean changePassword(Long userId, String oldPassword, String newPassword) {
        User user = getById(userId);
        if(user == null || !passwordEncoder.matches(oldPassword, user.getPassword())){
            throw new RuntimeException("wrong old password!");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        return updateById(user);
    }

    public boolean exists(String email){
        return count(new LambdaQueryWrapper<User>().eq(User::getEmail, email))>0;
    }
}
