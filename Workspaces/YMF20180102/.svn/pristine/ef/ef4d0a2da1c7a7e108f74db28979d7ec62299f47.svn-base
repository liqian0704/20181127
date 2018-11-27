package com.yeepay.g3.core.ymf.service.impl;

import com.alibaba.fastjson.JSON;
import com.yeepay.g3.core.ymf.dao.GroupDao;
import com.yeepay.g3.core.ymf.entity.Group;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.GroupService;
import com.yeepay.utils.jdbc.dal.utils.CheckUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *
 * Created by fei.lu on 16/8/13.
 */
@Service("groupService")
public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupDao  groupDao;

    @Autowired
    private CustomerService  customerService;
    
	@Override
	public List<Group> getGroupList() {
		// TODO Auto-generated method stub
		return groupDao.getGroupList();
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void insert(Group group) {
		groupDao.insert(group);
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateGroupName(String groupName, Long id) {
		groupDao.updateGroupName(groupName, id);
	}
	@Override
	public Group getGroupById(Long id) {
		// TODO Auto-generated method stub
		return groupDao.getGroupById(id);
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public String bindCustomers(String createUser,String groupName, Long[] customerIds) {
		if(CheckUtils.isEmpty(groupName)) return "请选择或填写一级商户";
		if(CheckUtils.isEmpty(customerIds)) return "请选择要绑定的二级商户";
		Group group = groupDao.getGroupByName(groupName.trim());
		if(group==null){
			group = new Group();
			group.setGroupName(groupName);
			group.setCreateUser(createUser);
			groupDao.insert(group);
		}
		for(Long customerId:customerIds){
			customerService.bindGroup(customerId, group.getId());
		}
		return "商户绑定成功.";
	}
	
}
