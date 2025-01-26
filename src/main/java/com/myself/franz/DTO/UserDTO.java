package com.myself.franz.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
