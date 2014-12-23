package com.blueshit.joke.validator;

import com.blueshit.joke.entity.Joke;
import com.blueshit.joke.entity.Proposal;
import com.blueshit.joke.service.JokeService;
import com.blueshit.joke.service.ProposalService;
import com.blueshit.joke.utils.CheckUtil;
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
public class JokeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return JokeService.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Joke joke = (Joke) target;
        if (joke != null) {
            if(joke.getStyle() == 1){//图片
                if(joke.getPicture() == null || joke.getPicture().isEmpty()) {
                    errors.rejectValue("picture", "图片不能为空", "图片不能为空");
                }else if(joke.getPicture().equals("sizeError")){
                    errors.rejectValue("picture", "图片最大(800*4000)", "图片最大(800*4000)");
                }
            }
            if(joke.getStyle() == 2){
                if(joke.getContent() == null || joke.getContent().isEmpty()) {
                    errors.rejectValue("content", "内容不能为空", "内容不能为空");
                }else if(joke.getContent().length()>200 || joke.getContent().length()<10){
                    errors.rejectValue("content","字数限制(10-200)","字数限制(10-200)");
                }
            }
            if(joke.getTitle() == null || joke.getTitle().isEmpty()){
                errors.rejectValue("title","标题不能为空","标题不能为空");
            }else if(joke.getTitle().length()>100 || joke.getTitle().length() <5){
                errors.rejectValue("title","字数限制(5-100)","字数限制(5-100)");
            }
        } else{
            errors.rejectValue("title", "提交内容不能为空", "提交内容不能为空");
        }
    }
}
