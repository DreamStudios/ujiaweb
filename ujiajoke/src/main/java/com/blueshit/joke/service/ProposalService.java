package com.blueshit.joke.service;

import com.blueshit.joke.entity.Proposal;

/**
 * 意见处理接口
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/18 15:15
 * @description
 */
public interface ProposalService {

    /**
     * 保存用户意见建议
     * @param proposal
     * @return
     */
    public boolean proposalSave(Proposal proposal);
}
