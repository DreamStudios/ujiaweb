package com.blueshit.joke.validator;

import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.service.UserInfoService;
import com.blueshit.joke.utils.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 用户信息校验
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/11 18:18
 * @description
 */
@Component
public class UserInfoValidator implements Validator {

    UserInfoService userInfoService;
    @Autowired
    public UserInfoValidator(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserInfoService.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserInfo userInfo = (UserInfo) target;
        if (userInfo != null) {
            //判断email
            if (userInfo.getEmail() == null || userInfo.getEmail().length() == 0) {
                errors.rejectValue("email", "邮箱不能为空","邮箱不能为空");
            }else{
                if (!CheckUtil.isEmail(userInfo.getEmail())){
                    errors.rejectValue("email", "邮箱格式错误", "邮箱格式错误");
                }else{
                    UserInfo userInfoTemp = userInfoService.getUserByEmail(userInfo.getEmail());
                    if (userInfoTemp != null) {
                        errors.rejectValue("email", "用户已存在", "用户已存在");
                    }
                }
            }
            //判断用户姓名
            if(userInfo.getName() == null || userInfo.getName().length() == 0){
                errors.rejectValue("name", "昵称不能为空", "昵称不能为空");
            }
            //判断密码
            if (userInfo.getPassword() == null || userInfo.getPassword().length() == 0){
                errors.rejectValue("password","密码不能为空", "密码不能为空");
            }else{
                if (userInfo.getPassword().length() < 6 || userInfo.getPassword().length() > 32) {
                    errors.rejectValue("password", "密码为6至32位", "密码为6至32位");
                }
            }
        } else{
            errors.rejectValue("email", "注册信息不能为空");
        }
    }
}
