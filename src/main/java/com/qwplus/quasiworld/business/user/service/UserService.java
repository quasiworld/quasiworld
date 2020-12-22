package com.qwplus.quasiworld.business.user.service;

import com.qwplus.quasiworld.business.user.dao.UserRepository;
import com.qwplus.quasiworld.common.exception.ExceptionCast;
import com.qwplus.quasiworld.common.web.UserContext;
import com.qwplus.quasiworld.model.User;
import com.qwplus.quasiworld.model.bo.UserRegisterBO;
import com.qwplus.quasiworld.model.vo.UserVO;
import com.qwplus.quasiworld.util.CookieUtils;
import com.qwplus.quasiworld.common.web.WebContext;
import com.qwplus.quasiworld.util.JsonUtils;
import com.qwplus.quasiworld.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    @Value("${COOKIE.TOKEN.KEY}")
    private String TOKEN;

    @Autowired
    private UserRepository userRepository;

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    public void register(UserRegisterBO userRegisterBO) {
        User user = User.builder()
                .status(true)
                .createDate(new Date())
                .passWord(userRegisterBO.getPassword())
                .phone(userRegisterBO.getPhone())
                .userName(userRegisterBO.getUserName())
                .build();
        if (userRepository.save(user) != null) {
            logger.info("用户注册成功" + user.getUserName());
        }
    }

    public void login(String userName, String password) {
        //查找登录用户
        Optional<User> optional = userRepository.findByUserName(userName);
        if (!optional.isPresent()) {
            ExceptionCast.cast("用户账户不存在");
        }
        User user = optional.get();
        if (MD5.MD5(password).equals(user.getPassWord())) {
            ExceptionCast.cast("账户或密码错误");
        }
        //生成唯一的token
        String token = UUID.randomUUID().toString();
        UserContext userContext = new UserContext();
        userContext.setToken(token);
        userContext.setUserId(user.getId());
        userContext.setUserName(user.getUserName());
        String json = JsonUtils.objectToJson(userContext);
        //记录token
        CookieUtils.setCookie(WebContext.getRequest(), WebContext.getResponse(), TOKEN, json);
    }


    public UserVO getUserInfo() {
        Optional<User> byId = userRepository.findById(WebContext.getUser().getUserId());
        if (!byId.isPresent()) {
            ExceptionCast.cast("找不到该用户");
        }
        User user = byId.get();
        UserVO userVO = new UserVO();
        userVO.setPhone(user.getPhone());
        userVO.setUserId(user.getId());
        userVO.setUserName(user.getUserName());
        return userVO;
    }

    public void logout() {
        CookieUtils.delCookieByName(WebContext.getRequest(),WebContext.getResponse(),TOKEN);
    }
}
