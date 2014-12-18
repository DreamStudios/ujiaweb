package com.blueshit.joke.validator;

import com.blueshit.joke.entity.Proposal;
import com.blueshit.joke.entity.UserInfo;
import com.blueshit.joke.service.ProposalService;
import com.blueshit.joke.service.UserInfoService;
import com.blueshit.joke.utils.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 意见建议信息校验
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/11 18:18
 * @description
 */
@Component
public class ProposalValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProposalService.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Proposal proposal = (Proposal) target;
        if (proposal != null) {
            //判断建议内容
            if(proposal.getContent() == null || proposal.getContent().length() == 0){
                errors.rejectValue("content", "意见内容不能为空", "意见内容不能为空");
            }else{
                if (proposal.getContent().length() < 10 || proposal.getContent().length() > 100) {
                    errors.rejectValue("content", "内容为10-100位", "内容为10-100位");
                }
            }
            //判断QQ
            if (proposal.getQq() == null || proposal.getQq().length() < 4){
                errors.rejectValue("qq","请留下您的QQ", "请留下您的QQ");
            }else {
                if (!CheckUtil.isQQ(proposal.getQq())){
                    errors.rejectValue("qq", "QQ格式错误", "QQ格式错误");
                }
            }
        } else{
            errors.rejectValue("content", "提交内容不能为空", "提交内容不能为空");
        }
    }
}
