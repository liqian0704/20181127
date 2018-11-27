package com.yeepay.g3.core.ymf.service.shop.impl;

import com.yeepay.g3.core.ymf.dao.shop.ShopMapper;
import com.yeepay.g3.core.ymf.entity.shop.Shop;
import com.yeepay.g3.core.ymf.entity.shop.ShopParam;
import com.yeepay.g3.core.ymf.service.shop.ShopService;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.core.ymf.utils.sequence.SequenceUtils;
import com.yeepay.g3.facade.ymf.enumtype.common.ShopStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/6/21
 * @Time: 下午2:19
 */
@Service("shopService")
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int save(Shop shop) {
        if (shop == null) {
            throw new RuntimeException("Save record Shop is null ");
        }
        return defaulSave(shop);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int save(OperateEntity<Shop> shop) {
        if (shop == null) {
            throw new RuntimeException("Save record Shop is null ");
        }
        return defaulSave(shop.getEntity());
    }

    /**
     * 保存或更新
     *
     * @param shop
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int saveOrUpdate(Shop shop) {
        if (shop == null) {
            throw new RuntimeException("Save record Shop is null ");
        }
        if (shop.getId() == null || shop.getId() == 0) {
            return defaulSave(shop);
        } else {
            return shopMapper.updateByPrimaryKey(shop);
        }
    }

    /**
     * 保存或更新
     *
     * @param shop
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int saveOrUpdate(OperateEntity<Shop> shop) {
        if (shop == null) {
            throw new RuntimeException("Save record Shop is null ");
        }
        if (shop.getEntity().getId() == null || shop.getEntity().getId() == 0) {
            return defaulSave(shop.getEntity());
        } else {
            return shopMapper.updateByPrimaryKey(shop.getEntity());
        }
    }

    /**
     * 保存，补充默认值
     * @param shop
     * @return
     */
    private int defaulSave(Shop shop) {
        shop.setCreateTime(new Date());
        shop.setStatus(ShopStatus.ACTIVE);
        int insert = shopMapper.insert(shop);
        createShopNumber(shop);
        return insert;
    }

    /**
     * 更新(全量)
     *
     * @param shop
     * @return
     */
    @Override
    public int update(Shop shop) {
        if (shop == null) {
            throw new RuntimeException("Update record Shop is null ");
        }
        if (shop.getId() == null || shop.getId() == 0) {
            throw new RuntimeException("Update record Id is null ");
        }
        return shopMapper.updateByPrimaryKey(shop);
    }

    /**
     * 更新（选择）
     *
     * @param shop
     * @return
     */
    @Override
    public int updateSelective(Shop shop) {
        if (shop == null) {
            throw new RuntimeException("Update record Shop is null ");
        }
        if (shop.getId() == null || shop.getId() == 0) {
            throw new RuntimeException("Update record Id is null ");
        }
        return shopMapper.updateByPrimaryKeySelective(shop);
    }

    @Override
    public Shop queryShopById(Long id) {
        if (id == null || id == 0) {
            throw new RuntimeException("Query shop param id is null ");
        }
        Shop shop = shopMapper.selectByPrimaryKey(id);
        return shop;
    }


    @Override
    public List<Shop> queryShopByCustomerNumber(String customerNumber,ShopStatus status) {
        if (customerNumber == null) {
            throw new RuntimeException("Query shop param customerNumber is null ");
        }
        ShopParam param = new ShopParam();
        param.createCriteria().andCustomerNumberEqualTo(customerNumber).andStatusEqualTo(status);
        List<Shop> shops = shopMapper.selectByFilter(param);
        return shops;
    }


    @Override
    public Shop queryShopByShopNumber(String shopNumber,ShopStatus status) {
        if (shopNumber == null) {
            throw new RuntimeException("Query shop param shopNumber is null ");
        }
        ShopParam param = new ShopParam();
        ShopParam.Criteria criteria = param.createCriteria();
        criteria.andShopNumberEqualTo(shopNumber);
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        List<Shop> shops = shopMapper.selectByFilter(param);
        if (shops != null && shops.size() > 0) {
            return shops.get(0);
        }
        return null;
    }


    /**
     * 更新ShopNumber
     */
    private void createShopNumber(Shop shop) {
        //根据id更新shopNumber
        String shopNumber = SequenceUtils.createShopNumber(shop.getId());
        shop.setShopNumber(shopNumber);

        Shop upShop = new Shop();
        upShop.setId(shop.getId());
        upShop.setShopNumber(shopNumber);
        shopMapper.updateByPrimaryKeySelective(upShop);
    }

}
