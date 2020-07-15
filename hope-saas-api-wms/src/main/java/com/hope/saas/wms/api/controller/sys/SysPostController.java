package com.hope.saas.wms.api.controller.sys;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.hope.saas.common.entity.util.Result;
import com.hope.saas.common.entity.wms.CustomerInfoEntity;
import com.hope.saas.common.entity.wms.sys.SysPostEntity;
import com.hope.saas.common.entity.wms.sys.SysPostMenuEntity;
import com.hope.saas.common.entity.wms.sys.SysUserPostEntity;
import com.hope.saas.common.table.wms.CustomerInfoTable;
import com.hope.saas.common.table.wms.sys.SysPostMenuTable;
import com.hope.saas.common.table.wms.sys.SysUserPostTable;
import com.hope.saas.wms.api.service.CustomerInfoService;
import com.hope.saas.wms.api.service.sys.SysPostMenuService;
import com.hope.saas.wms.api.service.sys.SysPostService;
import com.hope.saas.wms.api.service.sys.SysUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 职位表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Controller
@RequestMapping(value = "post")
public class SysPostController {

    @Autowired
    private SysPostService sysPostService;

    @Autowired
    private SysUserPostService sysUserPostService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private SysPostMenuService sysPostMenuService;


    /**
     * 界面
     *
     * @return
     * @throws Exception
     */
    @GetMapping("list.htm")
    public String list() throws Exception {
        return "post/list";
    }

    /**
     * @param orgId
     * @return
     * @throws Exception
     */
    @GetMapping("selectList.htm")
    public String selectUserList(Long orgId, Model model) throws Exception {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (orgId != null) {
            entityWrapper.eq(CustomerInfoTable.ORG_ID, orgId);
        }
        List list = sysPostService.selectList(entityWrapper);
        model.addAttribute("list", list);
        return "post/postList";
    }


    /**
     * 保存界面
     *
     * @return
     * @throws Exception
     */
    @GetMapping("savePage.htm")
    public String savePage(Long id, Model model) throws Exception {
        SysPostEntity entity;
        if (id == null) {
            entity = new SysPostEntity();
        } else {
            entity = sysPostService.selectById(id);
        }
        if (entity == null) {
            entity = new SysPostEntity();
        }
        model.addAttribute("user", entity);
        return "post/save";
    }

    /**
     * 保存数据
     *
     * @param sysPostEntity
     * @return
     * @throws Exception
     */
    @PostMapping("save.htm")
    @ResponseBody
    public Result save(SysPostEntity sysPostEntity) throws Exception {
        boolean flag;
        if (sysPostEntity.getId() != null) {
            //TODO MD5
            flag = sysPostService.updateById(sysPostEntity);
        } else {
            flag = sysPostService.insert(sysPostEntity);
        }
        if (flag) {
            return Result.success("保存成功");
        }
        return Result.error("保存失败");
    }

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("delete.htm")
    @ResponseBody
    public Result delete(Long id) throws Exception {
        boolean flag = sysPostService.deleteById(id);
        if (flag) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 保存用户界面
     *
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("saveUserPage.htm")
    public String saveUserPage(@NotNull(message = "参数异常") Long id, Model model) throws Exception {
        List list = customerInfoService.selectList(new EntityWrapper<>());
        model.addAttribute("list", list);
        model.addAttribute("postId", id);

        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq(SysUserPostTable.POST_ID, id);
        List<SysUserPostEntity> ls = sysUserPostService.selectList(entityWrapper);

        List<Long> entities = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(ls)) {
            for (SysUserPostEntity l : ls) {
                entities.add(l.getUserId());
            }
        }
        List<CustomerInfoEntity> userList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(entities)) {
            userList = customerInfoService.selectBatchIds(entities);
        }
        model.addAttribute("userList", userList);
        return "post/saveUser";
    }


    /**
     * 保存职位用户
     *
     * @param id
     * @param userIds
     * @return
     * @throws Exception
     */
    @PostMapping("saveUser.htm")
    @ResponseBody
    public Result saveUser(@NotNull(message = "参数异常") Long id, Long[] userIds) throws Exception {
        boolean flag = sysUserPostService.saveUser(id, userIds);
        if (flag) {
            return Result.success("保存成功");
        }
        return Result.success("保存失败");
    }


    /**
     * 权限数据
     *
     * @return
     * @throws Exception
     */
    @GetMapping("authority.htm")
    public String authority(Model model, Long postId) throws Exception {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq(SysPostMenuTable.POST_ID, postId);
        List<SysPostMenuEntity> list = sysPostMenuService.selectList(entityWrapper);
        String str = "";
        if (list != null) {
            for (SysPostMenuEntity entity : list) {
                str += "," + entity.getMenuId();
            }
        }
        str = str.replaceFirst(",", "");
        model.addAttribute("str", str);
        model.addAttribute("postId", postId);
        return "post/authority";
    }

    /**
     * 保存权限
     *
     * @return
     */
    @PostMapping("saveAuthority.htm")
    @ResponseBody
    public Result saveAuthority(Long[] menuIds, Long postId) {
        sysPostMenuService.save(postId, menuIds);
        return Result.success("保存权限成功");
    }
}