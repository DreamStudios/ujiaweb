package com.blueshit.joke.service.impl;

import com.blueshit.joke.entity.Proposal;
import com.blueshit.joke.repository.ProposalRepository;
import com.blueshit.joke.service.ProposalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 意见建议处理接口实现类
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/18 15:15
 * @description
 */
@Service
public class ProposalServiceImpl implements ProposalService {
    private static final Logger logger = LoggerFactory.getLogger(ProposalServiceImpl.class);

    private ProposalRepository proposalRepository;

    @Autowired
    public ProposalServiceImpl(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    /**
     * 保存用户意见建议
     * @param proposal
     * @return
     */
    @Override
    public boolean proposalSave(Proposal proposal) {
        try {
            proposalRepository.save(proposal);
        } catch (Exception ex) {
            logger.error("用户反馈意见存储失败",ex);
            return false;
        }
        return true;
    }
}
