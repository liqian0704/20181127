package com.yeepay.g3.ymf.boss.controller.likerapp;

import com.google.common.collect.Lists;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.boss.FuncRoleDTO;
import com.yeepay.g3.facade.laike.dto.boss.RoleDTO;
import com.yeepay.g3.facade.laike.dto.boss.RoleListDTO;
import com.yeepay.g3.facade.laike.enumtype.FuncLevelEnum;
import com.yeepay.g3.facade.laike.facade.UserPermissionFacade;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Description: 权限管理
 * Author: jiawen.huang
 * Date: 2017/8/31
 * Time: 15:09
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Controller
@RequestMapping("/user/permission")
public class UserPermissionController extends ValidateController {

    private static final Logger log = LoggerFactory.getLogger(UserPermissionController.class);

	@RequestMapping("/role/query")
	public String roleQuery() {
		return "likerapp/permission/roleQuery";
	}

	@RequestMapping("/role/toModify")
	public String role2Modify(@RequestParam String id, Model model) {
		UserPermissionFacade userPermissionFacade = RemoteServiceFactory.getService(UserPermissionFacade.class);
		RoleDTO roleDTO = userPermissionFacade.findRoleById(id);
		RoleListDTO roleList = userPermissionFacade.listRoles();
		model.addAttribute("role", roleDTO);
		model.addAttribute("roleList", convert2Json(roleList, ""));
        return "likerapp/permission/roleModify";
	}

    @RequestMapping("/role/modify")
    @ResponseBody
    public ResponseMessage roleModify(String id, String roleName, String description, String parentId, HttpSession session) {
        try {
            UserPermissionFacade userPermissionFacade = RemoteServiceFactory.getService(UserPermissionFacade.class);
            RoleDTO role = userPermissionFacade.findRoleById(id);
            role.setOperator(getUser(session));//getUser(session)
            role.setRoleName(roleName);
            role.setDescription(description);
            role.setParentId(parentId);
            BaseResponse response = userPermissionFacade.modifyRole(role);
            String errorCode = response.getErrCode();
            if(errorCode!=null){
                return ResponseMessage.error(response.getErrMsg());
            }
            return ResponseMessage.ok();
        }catch (Exception e){
            log.error("修改失败",e);
            return ResponseMessage.error(e.toString());
        }
    }

	@RequestMapping("/role/toAdd")
	public String role2Add(Model model) {
        UserPermissionFacade userPermissionFacade = RemoteServiceFactory.getService(UserPermissionFacade.class);
        RoleListDTO roleList = userPermissionFacade.listRoles();
        model.addAttribute("roleList", convert2Json(roleList, ""));
        return "likerapp/permission/roleAdd";
	}

    @RequestMapping("/role/add")
    @ResponseBody
    public ResponseMessage roleAdd(String roleCode, String roleName, String description, String parentId, HttpSession session) {
        try {
            UserPermissionFacade userPermissionFacade = RemoteServiceFactory.getService(UserPermissionFacade.class);
            RoleDTO role = new RoleDTO();
            role.setOperator(getUser(session));//getUser(session)
            role.setRoleName(roleName);
            role.setDescription(description);
            role.setParentId(parentId);
            role.setRoleCode(roleCode);
            BaseResponse response = userPermissionFacade.createRole(role);
            String errorCode = response.getErrCode();
            if(errorCode!=null){
                return ResponseMessage.error(response.getErrMsg());
            }
            return ResponseMessage.ok();
        }catch (Exception e){
            log.error("修改失败",e);
            return ResponseMessage.error(e.toString());
        }
    }

    @RequestMapping("/function/role/query")
    public String funcRoleQuery(@RequestParam String id, Model model) {
        UserPermissionFacade userPermissionFacade = RemoteServiceFactory.getService(UserPermissionFacade.class);
        RoleDTO roleDTO = userPermissionFacade.findRoleById(id);
        model.addAttribute("role", roleDTO);
        return "likerapp/permission/funcRoleQuery";
    }

    @RequestMapping("/function/role/toModify")
    public String funcRole2Modify(@RequestParam Long id, Model model) {
        UserPermissionFacade userPermissionFacade = RemoteServiceFactory.getService(UserPermissionFacade.class);
        FuncRoleDTO funcRoleDTO = userPermissionFacade.findFuncById(id);
        RoleListDTO roleList = userPermissionFacade.listRoles();
        model.addAttribute("roleList", convert2Json(roleList, ""));
        model.addAttribute("funcRole", funcRoleDTO);
        model.addAttribute("roleIds", JSONUtils.toJsonString(funcRoleDTO.getRoleIds()));
        model.addAttribute("funcLevelList", JSONUtils.toJsonString(funcRoleDTO.getFuncLevelList()));
        return "likerapp/permission/funcRoleModify";
    }

    @RequestMapping("/function/role/toggleFunc")
    @ResponseBody
    public ResponseMessage funcRoleToggle(@RequestParam Long id, HttpSession session) {
        try {
            UserPermissionFacade userPermissionFacade = RemoteServiceFactory.getService(UserPermissionFacade.class);
            BaseResponse response = userPermissionFacade.toggleRoleFunc(id, getUser(session), "");//getUser(session)
            String errorCode = response.getErrCode();
            if(errorCode!=null){
                return ResponseMessage.error(response.getErrMsg());
            }
            return ResponseMessage.ok();
        }catch (Exception e){
            log.error("修改失败",e);
            return ResponseMessage.error(e.toString());
        }
    }

    @RequestMapping("/function/role/modify")
    @ResponseBody
    public ResponseMessage funcRoleModify(Long id, String funName, String funCode, String funLevel, String[] array, HttpSession session) {
        try {
            UserPermissionFacade userPermissionFacade = RemoteServiceFactory.getService(UserPermissionFacade.class);
            FuncRoleDTO funcRoleDTO = new FuncRoleDTO();
            funcRoleDTO.setId(id);
            funcRoleDTO.setFunctionName(funName);
            funcRoleDTO.setFunctionCode(funCode);
            funcRoleDTO.setOperator(getUser(session));//getUser(session)
            funcRoleDTO.setFuncLevelList(convert2FuncLevelList(funLevel));
            funcRoleDTO.setRoleIds(convert2RoleList(array));
            BaseResponse response = userPermissionFacade.modifyFunc(funcRoleDTO);
            String errorCode = response.getErrCode();
            if(errorCode!=null){
                return ResponseMessage.error(response.getErrMsg());
            }
            return ResponseMessage.ok();
        }catch (Exception e){
            log.error("修改失败",e);
            return ResponseMessage.error(e.toString());
        }
    }

    @RequestMapping("/function/add")
    @ResponseBody
    public ResponseMessage functionAdd(String funName, String funCode, String funLevel, String[] array, HttpSession session) {
        try {
            UserPermissionFacade userPermissionFacade = RemoteServiceFactory.getService(UserPermissionFacade.class);
            FuncRoleDTO funcRoleDTO = new FuncRoleDTO();
            funcRoleDTO.setFunctionName(funName);
            funcRoleDTO.setFunctionCode(funCode);
            funcRoleDTO.setOperator(getUser(session));//getUser(session)
            funcRoleDTO.setFuncLevelList(convert2FuncLevelList(funLevel));
            funcRoleDTO.setRoleIds(convert2RoleList(array));
            BaseResponse response = userPermissionFacade.createFunc(funcRoleDTO);
            String errorCode = response.getErrCode();
            if(errorCode!=null){
                return ResponseMessage.error(response.getErrMsg());
            }
            return ResponseMessage.ok();
        }catch (Exception e){
            log.error("新增失败",e);
            return ResponseMessage.error(e.toString());
        }
    }

	@RequestMapping("/function/query")
	public String functionQuery() {
		return "likerapp/permission/functionQuery";
	}

    @RequestMapping("/function/toAdd")
    public String function2Add(Model model) {
        UserPermissionFacade userPermissionFacade = RemoteServiceFactory.getService(UserPermissionFacade.class);
        RoleListDTO roleList = userPermissionFacade.listRoles();
        model.addAttribute("roleList", convert2Json(roleList, ""));
        return "likerapp/permission/functionAdd";
    }

    @RequestMapping("/function/toggleFunc")
    @ResponseBody
    public ResponseMessage functionToggle(@RequestParam Long id, HttpSession session) {
        try {
            UserPermissionFacade userPermissionFacade = RemoteServiceFactory.getService(UserPermissionFacade.class);
            BaseResponse response = userPermissionFacade.toggleFunc(id, getUser(session), "");//getUser(session)
            String errorCode = response.getErrCode();
            if(errorCode!=null){
                return ResponseMessage.error(response.getErrMsg());
            }
            return ResponseMessage.ok();
        }catch (Exception e){
            log.error("修改失败",e);
            return ResponseMessage.error(e.toString());
        }
    }

    /**
     * 转换json
     *
     * @param obj
     * @param id
     * @return
     */
	private String convert2Json(Object obj, String id) {
        List list = Lists.newArrayList();
        if (obj instanceof RoleListDTO) {
            for (RoleDTO role : ((RoleListDTO) obj).getRoleDTOList()) {
                if (!role.getId().equals(id)) {
                    list.add(role);
                }
            }
            return JSONUtils.toJsonString(list);
        } else {
            for (String roleId : (List<String>) obj) {
                if (!roleId.equals(id)) {
                    list.add(roleId);
                }
            }
            return JSONUtils.toJsonString(obj);
        }
    }

    /**
     * 转换FuncLevelEnum
     *
     * @param funLevel
     * @return
     */
    private List<FuncLevelEnum> convert2FuncLevelList(String funLevel){
            List list = Lists.newArrayList();
            String[] array = StringUtils.split(funLevel, ",");
            for (String s : array){
                list.add(FuncLevelEnum.valueOf(s));
            }
            return list;
    }

    private List<String> convert2RoleList(String[] array){
        List list = Lists.newArrayList();
        for (String s : array){
            list.add(s);
        }
        return list;
    }
}
