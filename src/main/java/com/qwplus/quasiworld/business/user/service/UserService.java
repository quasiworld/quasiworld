package com.qwplus.quasiworld.business.user.service;

import com.qwplus.quasiworld.business.user.dao.UserRepository;
import com.qwplus.quasiworld.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    public Boolean register(User user) {

        if (userRepository.save(user) != null) {
            logger.info("用户注册成功" + user);
            return true;
        }
        return false;
    }
}
