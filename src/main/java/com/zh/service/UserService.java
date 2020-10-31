package com.zh.service;

import com.zh.pojo.User;

public interface UserService {
    User checkUser(String username, String password);
}
