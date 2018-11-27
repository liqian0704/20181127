package com.yeepay.g3.core.ymf.service.impl.cod;

import com.yeepay.g3.core.ymf.dao.cod.CodNotifyInfoDao;
import com.yeepay.g3.core.ymf.dao.cod.CodNotifyInfoMapper;
import com.yeepay.g3.core.ymf.entity.cod.CodNotifyInfo;
import com.yeepay.g3.core.ymf.entity.cod.CodNotifyInfoParam;
import com.yeepay.g3.core.ymf.service.cod.CodNotifyInfoService;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyArgs;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CodNotifyInfoServiceImpl implements CodNotifyInfoService {
    @Autowired
    private CodNotifyInfoDao codNotifyInfoDao;

    @Autowired
    private CodNotifyInfoMapper codNotifyInfoMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int save(CodNotifyInfo codNotifyInfo) {
		return codNotifyInfoMapper.insert(codNotifyInfo);
	}

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int updateById(CodNotifyInfo codNotifyInfo) {
		return codNotifyInfoMapper.updateByPrimaryKey(codNotifyInfo) ;
	}

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updatePartial(CodNotifyInfo codNotifyInfo) {
        return codNotifyInfoMapper.updateByPrimaryKeySelective(codNotifyInfo);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int updateStateById(CodNotifyInfo codNotifyInfo) {
		return codNotifyInfoDao.updateStateById(codNotifyInfo) ;
	}

	@Override
	public List<CodNotifyInfo> selectByFilter(CodNotifyInfoParam codNotifyInfo) {
		return codNotifyInfoMapper.selectByFilter(codNotifyInfo);
	}

	@Override
	public List<CodNotifyDTO> queryDTOByArgs(CodNotifyArgs args) {
		return codNotifyInfoDao.queryDTOByArgs(args);
	}

	@Override
	public CodNotifyInfo queryByArgs(CodNotifyArgs args) {
		List<CodNotifyInfo> list = codNotifyInfoDao.queryByArgs(args);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
