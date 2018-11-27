package com.yeepay.g3.core.ymf.service.cod;

import com.yeepay.g3.core.ymf.entity.cod.CodNotifyInfo;
import com.yeepay.g3.core.ymf.entity.cod.CodNotifyInfoParam;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyArgs;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyDTO;

import java.util.List;

/**
 * COD异步通知记录
 * @author xiaobin.liu
 *
 */
public interface CodNotifyInfoService {
	
	/**
     * 新增
     * @param codNotifyInfo
     */
    int save(CodNotifyInfo codNotifyInfo);

    /**
     * 更新状态
     */
    int updateStateById(CodNotifyInfo codNotifyInfo) ;

	/**
	 * 根据主键修改, 参数填什么就更新什么
	 * @param codNotifyInfo
	 * @return
     */
	int updateById(CodNotifyInfo codNotifyInfo);

	/**
	 * 根据参数的非空字段修改
	 * @param codNotifyInfo
	 * @return
     */
	int updatePartial(CodNotifyInfo codNotifyInfo);

	/**
	 * 根据过滤条件查询。extendId为必填项
	 */
	List<CodNotifyInfo> selectByFilter(CodNotifyInfoParam codNotifyInfoParam);

    /**
	 * 根据条件查询
	 * @param args 参数
	 * @return
     */
	List<CodNotifyDTO> queryDTOByArgs(CodNotifyArgs args);

	/**
	 *
	 * @param args
	 * @return
     */
	CodNotifyInfo queryByArgs(CodNotifyArgs args);
}
