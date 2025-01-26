package com.myself.franz.Converter;

import com.myself.franz.DTO.UserDTO;
import com.myself.franz.entity.User;
import org.springframework.beans.BeanUtils;

public class BeanConverter {
    public static UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    public static User toUser(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }
}
