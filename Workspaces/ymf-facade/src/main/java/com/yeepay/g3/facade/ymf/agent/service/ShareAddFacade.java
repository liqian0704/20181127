package com.yeepay.g3.facade.ymf.agent.service;


import com.yeepay.g3.facade.ymf.agent.bean.ShareRequestDTO;
import com.yeepay.g3.facade.ymf.agent.bean.ShareResponseDTO;

import java.util.List;

/**
 * Created by guangzong.yan on 18/1/9.
 */
public interface ShareAddFacade {

    /**
     * 添加分润
     * @param shareRequestDTO
     * @return
     */
   public ShareResponseDTO addAgentShare(ShareRequestDTO shareRequestDTO);

    /**
     * 添加分润
     * @param list
     * @return
     */
    public ShareResponseDTO addAgentShare(List<ShareRequestDTO> list);
}
