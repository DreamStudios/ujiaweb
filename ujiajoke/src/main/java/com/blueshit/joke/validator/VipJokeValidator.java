package com.blueshit.joke.validator;

import com.blueshit.joke.entity.VipJoke;
import com.blueshit.joke.service.JokeService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 提交普通笑话校验校验
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/11 18:18
 * @description
 */
@Component
public class VipJokeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return JokeService.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        VipJoke vipJoke = (VipJoke) target;
        if (vipJoke != null) {
            if(vipJoke.getStyle() == 1){//图片
                if(vipJoke.getPicture() == null || vipJoke.getPicture().isEmpty()) {
                    errors.rejectValue("picture", "图片不能为空", "图片不能为空");
                }else if(vipJoke.getPicture().equals("sizeError")){
                    errors.rejectValue("picture", "图片最大(800*4000)", "图片最大(800*4000)");
                }
            }
            if(vipJoke.getStyle() == 2){
                if(vipJoke.getContent() == null || vipJoke.getContent().isEmpty()) {
                    errors.rejectValue("content", "内容不能为空", "内容不能为空");
                }else if(vipJoke.getContent().length()>200 || vipJoke.getContent().length()<10){
                    errors.rejectValue("content","字数限制(10-200)","字数限制(10-200)");
                }
            }
            if(vipJoke.getTitle() == null || vipJoke.getTitle().isEmpty()){
                errors.rejectValue("title","标题不能为空","标题不能为空");
            }else if(vipJoke.getTitle().length()>100 || vipJoke.getTitle().length() <5){
                errors.rejectValue("title","字数限制(5-100)","字数限制(5-100)");
            }
        } else{
            errors.rejectValue("title", "提交内容不能为空", "提交内容不能为空");
        }
    }
}
