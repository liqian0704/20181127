package com.yeepay.g3.core.ymf.dao.cod;

import com.yeepay.g3.core.ymf.entity.cod.CodNotifyInfo;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyArgs;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodNotifyInfoDao {

	int updateStateById(CodNotifyInfo codNotifyInfo) ;

	/**
	 * 根据参数查询DTO
	 * @param args 参数
	 * @return
     */
	List<CodNotifyDTO> queryDTOByArgs(CodNotifyArgs args);

	/**
	 * 根据参数查询实体
	 * @param args
	 * @return
     */
	List<CodNotifyInfo> queryByArgs(CodNotifyArgs args);
}