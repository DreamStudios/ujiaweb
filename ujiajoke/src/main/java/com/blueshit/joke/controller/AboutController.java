package com.blueshit.joke.controller;

import com.blueshit.joke.entity.Proposal;
import com.blueshit.joke.service.ProposalService;
import com.blueshit.joke.validator.ProposalValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 关于有家笑话相关页面
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/18 13:55
 * @description
 */
@Controller
@RequestMapping("/about")
public class AboutController {

    private ProposalService proposalService;
    private ProposalValidator proposalValidator;

    @Autowired
    public AboutController(ProposalService proposalService, ProposalValidator proposalValidator) {
        this.proposalService = proposalService;
        this.proposalValidator = proposalValidator;
    }

    //用户提交反馈意见
    @RequestMapping(value = "/fankuiyijian", method = RequestMethod.POST)
    public String proposal(HttpServletRequest request,Model model,
                           @ModelAttribute("proposal") Proposal proposal,BindingResult result){
        proposalValidator.validate(proposal,result);
        String kaptchaExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (proposal.getKaptcha() == null || !proposal.getKaptcha().equalsIgnoreCase(kaptchaExpected)) {
            result.rejectValue("kaptcha", "请填写正确的验证码");
            model.addAttribute("kaptcha", "请填写正确的验证码");
        }

        if (!result.hasErrors()) {
            proposalService.proposalSave(proposal);
            model.addAttribute("message","您的意见已经提交给站长");
        }
        return "about/fankuiyijian";
    }

    /**
     * 进入介绍相关页面
     * @param name
     * @return
     */
    @RequestMapping({"/{name}.html"})
    public String aboutHtml(@PathVariable String name,Model model) {
        if("fankuiyijian".equals(name)) { //反馈意见
            Proposal proposal = new Proposal();
            model.addAttribute("proposal",proposal);
        }
        return "about/" + name;
    }
}
