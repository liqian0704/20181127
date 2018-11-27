package com.yeepay.g3.core.ymf.dao;

import com.yeepay.g3.core.ymf.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.*;
@Repository("groupDao")
public interface GroupDao {

    List<Group> getGroupList();

    void insert(Group group);
    
    void updateGroupName(@Param("groupName")String groupName,@Param("id")Long id);
    
    Group getGroupById(@Param("id")Long id);
    
    Group getGroupByName(@Param("groupName")String groupName);
}