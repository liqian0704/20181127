package com.yeepay.g3.ymf.boss.controller.dictionary;

import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.service.DictionaryService;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.core.ymf.support.bean.DictionaryHolder;
import com.yeepay.g3.core.ymf.utils.BeanValidator;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.enumtype.OperateType;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import com.yeepay.g3.ymf.boss.support.annotations.CommonArgs;
import com.yeepay.g3.ymf.boss.support.annotations.DictBatchArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据字典配置
 * Created by fei.lu on 16/8/12.
 */
@Controller
@RequestMapping("dictionary")
public class DictionaryController extends ValidateController {

    private static final Logger log = LoggerFactory.getLogger(DictionaryController.class);

    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private DictionaryHolder dictionaryHolder;

    //根据类型获得数据字典类型
    @RequestMapping("getDictionariesByType")
    @ResponseBody
    public List<Dictionary> getDictionariesByType(@RequestParam String type) {
        return dictionaryService.getDictionariesByType(type);
    }

    /**
     * 查询路由
     * @return route
     */
    @RequestMapping("query")
    public String query() {
        return "dictionary/query";
    }

    /**
     * 新增路由
     * @return route
     */
    @RequestMapping("toAdd")
    public String toAdd() {
        return "dictionary/add";
    }

    /**
     * 新增
     * @param args
     * @param session
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResponseMessage add(@CommonArgs Dictionary args, HttpSession session) {
        ResponseMessage resp = BeanValidator.validate(args);
        if (resp.isOk()) {
            // 业务校验
            Dictionary dict = dictionaryService.getDictByTypeAndCode(args.getType(), args.getCode());
            if (null != dict) {
                return ResponseMessage.error("字典类型%s和字典编码%s已存在", args.getType(), args.getCode());
            }
            String operator = getUser(session);
            args.setCreateTime(new Date());
            dictionaryService.save(new OperateEntity<Dictionary>(operator, args, OperateType.ADD));
            return ResponseMessage.ok();
        }
        return resp;
    }

    /**
     * 批量新增路由
     * @return route
     */
    @RequestMapping("toBatchAdd")
    public String toBatchAdd() {
        return "dictionary/batchAdd";
    }


    /**
     * 批量新增
     * @param argsList args
     * @param session session
     * @return
     */
    @RequestMapping("batchAdd")
    @ResponseBody
    public ResponseMessage batchAdd(@DictBatchArgs List<Dictionary> argsList, HttpSession session) {
        List<OperateEntity<Dictionary>> wrapperList = new LinkedList<OperateEntity<Dictionary>>();
        String operator = getUser(session);
        for (Dictionary args : argsList) { // 倒序 可以移除
            ResponseMessage resp = validate(args);
            if (!resp.isOk()) { // 有一个校验不过就记录
                return resp;
            } else {
                // 业务校验
                Dictionary dict = dictionaryService.getDictByTypeAndCode(args.getType(), args.getCode());
                if (null != dict) {
                    return ResponseMessage.error("字典类型%s和字典编码%s已存在", args.getType(), args.getCode());
                } else {
                    args.setCreateTime(new Date());
                    OperateEntity<Dictionary> wrapper = new OperateEntity<Dictionary>(operator, args, OperateType.ADD);
                    wrapperList.add(wrapper);
                }
            }
        }
        if (wrapperList.size() > 0) {
            // 剩余还有的话
            int count = dictionaryService.batchSave(wrapperList);
            return ResponseMessage.data("新增字典记录条数" + count);
        }
        return ResponseMessage.ok();
    }

    /**
     * 修改路由
     * @return route
     */
    @RequestMapping("toModify")
    public String toModify(@RequestParam Long id, Model model) {
        Dictionary dict = dictionaryService.getById(id);
        if (null == dict) {
            model.addAttribute("msg", "数据字典不存在");
            return "error";
        } else {
            model.addAttribute("dict", dict);
            return "dictionary/modify";
        }
    }

    /**
     * 修改
     * @param args
     * @param session
     * @return
     */
    @RequestMapping("modify")
    @ResponseBody
    public ResponseMessage modify(@CommonArgs Dictionary args, HttpSession session) {
        ResponseMessage resp = BeanValidator.validate(args);
        if (resp.isOk()) {
            // 业务校验
            Dictionary dict = dictionaryService.getDictByTypeAndCode(args.getType(), args.getCode());
            if (null == dict) {
                return ResponseMessage.error("字典类型%s和字典编码%s不存在", args.getType(), args.getCode());
            }
            String operator = getUser(session);
            // 兼容时间设置
            if (null == dict.getCreateTime()) {
                args.setCreateTime(new Date());
            }
            args.setUpdateTime(new Date());
            dictionaryService.update(new OperateEntity<Dictionary>(operator, args, OperateType.UPDATE));
            return ResponseMessage.ok();
        }
        return resp;
    }

    /**
     * 批量修改
     * @param args
     * @param session
     * @return
     */
    @RequestMapping("batchUpdate")
    @ResponseBody
    public ResponseMessage batchUpdate(@CommonArgs Dictionary args, HttpSession session) {
        // TODO
        return ResponseMessage.ok();
    }

    /**
     * 删除字典数据
     * @param id 主键
     * @return
     */
    @RequestMapping("remove")
    @ResponseBody
    public ResponseMessage delete(@RequestParam Long id, HttpSession session) {
        Dictionary dict = dictionaryService.getById(id);
        if (null == dict) {
            return ResponseMessage.error("没有找到字典数据, id:" + id);
        }
        if (!dict.isCanDelete()) {
            return ResponseMessage.error("此字典数据不能删除, id:" + id);
        }
        try {
            dictionaryService.delete(new OperateEntity<Dictionary>(
                    getUser(session), dict, OperateType.DELETE));
            return ResponseMessage.ok();
        } catch (Exception e) {
            log.error("删除字典数据系统异常, id:" + id, e);
            return ResponseMessage.error("删除字典数据系统异常", e);
        }
    }

    /**
     * 刷新字典缓存
     * @return
     */
    @RequestMapping("refresh")
    @ResponseBody
    public ResponseMessage refreshCache() {
        dictionaryHolder.refresh();
        return ResponseMessage.ok();
    }
}
