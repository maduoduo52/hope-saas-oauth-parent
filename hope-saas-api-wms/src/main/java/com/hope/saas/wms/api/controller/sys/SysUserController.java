package com.hope.saas.wms.api.controller.sys;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hope.saas.api.util.util.MD5Utils;
import com.hope.saas.common.entity.util.Result;
import com.hope.saas.common.entity.wms.CustomerInfoEntity;
import com.hope.saas.common.entity.wms.sys.SysPostEntity;
import com.hope.saas.common.entity.wms.sys.SysUserMenuEntity;
import com.hope.saas.common.table.wms.CustomerInfoTable;
import com.hope.saas.common.table.wms.sys.SysUserMenuTable;
import com.hope.saas.wms.api.config.annotation.IgnoreLogin;
import com.hope.saas.wms.api.service.CustomerInfoService;
import com.hope.saas.wms.api.service.sys.SysPostService;
import com.hope.saas.wms.api.service.sys.SysUserMenuService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Maduo
 * @date 2020/5/22 23:42
 */
@Controller
@RequestMapping(value = "user")
public class SysUserController {

    @Autowired
    private SysPostService sysPostService;

    @Autowired
    private SysUserMenuService sysUserMenuService;

    @Autowired
    private CustomerInfoService customerInfoService;


    /**
     * 根据用户名称获取用户职位
     *
     * @param userName
     * @return
     * @throws Exception
     */
    @GetMapping("getPostListByUserName.htm")
    @ResponseBody
    @IgnoreLogin
    public Result getPostListByUserName(@NotNull(message = "登录名称不能为空") String userName) throws Exception {
        List<SysPostEntity> sysPostEntities = sysPostService.selectByLoginName(userName);
        return Result.success(sysPostEntities, "根据用户名称获取用户职位");
    }

    /**
     * 界面
     *
     * @return
     * @throws Exception
     */
    @GetMapping("list.htm")
    public String list() throws Exception {
        return "user/list";
    }

    /**
     * 获取用户信息
     *
     * @param orgId
     * @return
     * @throws Exception
     */
    @GetMapping("selectUserList.htm")
    public String selectUserList(Long orgId, Model model) throws Exception {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (orgId != null) {
            entityWrapper.eq(CustomerInfoTable.ORG_ID, orgId);
        }
        List list = customerInfoService.selectList(entityWrapper);
        model.addAttribute("list", list);
        return "user/userList";
    }


    /**
     * 保存界面
     *
     * @return
     * @throws Exception
     */
    @GetMapping("savePage.htm")
    public String savePage(Long id, Model model) throws Exception {
        CustomerInfoEntity entity;
        if (id == null) {
            entity = new CustomerInfoEntity();
        } else {
            entity = customerInfoService.selectById(id);
        }
        if (entity == null) {
            entity = new CustomerInfoEntity();
        }
        model.addAttribute("user", entity);
        return "user/save";
    }

    /**
     * 保存数据
     *
     * @param sysOrgEntity
     * @return
     * @throws Exception
     */
    @PostMapping("save.htm")
    @ResponseBody
    public Result save(CustomerInfoEntity sysOrgEntity) throws Exception {
        boolean flag;
        if (StringUtils.isBlank(sysOrgEntity.getPassword())) {
            return Result.error("密码不能为空");
        }
        if (sysOrgEntity.getId() != null) {
            CustomerInfoEntity entity = customerInfoService.selectById(sysOrgEntity.getId());
            sysOrgEntity.setPassword(MD5Utils.encryptByMD5(sysOrgEntity.getPassword() + entity.getSalt()));
            flag = customerInfoService.updateById(sysOrgEntity);
        } else {
            String salt = RandomStringUtils.randomAlphanumeric(10);
            sysOrgEntity.setPassword(MD5Utils.encryptByMD5(sysOrgEntity.getPassword() + salt));
            sysOrgEntity.setSalt(salt);
            sysOrgEntity.setUserKey(RandomStringUtils.randomAlphanumeric(16));
            flag = customerInfoService.insert(sysOrgEntity);
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
        boolean flag = customerInfoService.deleteById(id);
        if (flag) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 权限数据
     *
     * @return
     * @throws Exception
     */
    @GetMapping("authority.htm")
    public String authority(Model model, Long userId) throws Exception {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq(SysUserMenuTable.USER_ID, userId);
        List<SysUserMenuEntity> list = sysUserMenuService.selectList(entityWrapper);
        String str = "";
        if (list != null) {
            for (SysUserMenuEntity entity : list) {
                str += "," + entity.getMenuId();
            }
        }
        str = str.replaceFirst(",", "");
        model.addAttribute("str", str);
        model.addAttribute("userId", userId);
        return "user/authority";
    }

    /**
     * 保存权限
     *
     * @return
     */
    @PostMapping("saveAuthority.htm")
    @ResponseBody
    public Result saveAuthority(Long[] menuIds, Long userId) {
        sysUserMenuService.save(userId, menuIds);
        return Result.success("保存权限成功");
    }
}
