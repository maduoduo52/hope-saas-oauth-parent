package com.hope.saas.wms.api.controller.sys;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hope.saas.api.util.exception.NewHopeException;
import com.hope.saas.api.util.util.AESCodeUtil;
import com.hope.saas.api.util.util.MD5Utils;
import com.hope.saas.common.constants.Constant;
import com.hope.saas.common.entity.util.Result;
import com.hope.saas.common.entity.wms.CustomerInfoEntity;
import com.hope.saas.common.entity.wms.sys.SysMenuEntity;
import com.hope.saas.common.entity.wms.sys.SysPostMenuEntity;
import com.hope.saas.common.entity.wms.sys.SysUserMenuEntity;
import com.hope.saas.common.table.wms.CustomerInfoTable;
import com.hope.saas.common.table.wms.sys.SysPostMenuTable;
import com.hope.saas.common.table.wms.sys.SysUserMenuTable;
import com.hope.saas.wms.api.config.LoginConfig;
import com.hope.saas.wms.api.config.annotation.IgnoreLogin;
import com.hope.saas.wms.api.config.constant.LoginConstant;
import com.hope.saas.wms.api.config.redis.RedisTemplateUtils;
import com.hope.saas.wms.api.service.CustomerInfoService;
import com.hope.saas.wms.api.service.sys.SysMenuService;
import com.hope.saas.wms.api.service.sys.SysPostMenuService;
import com.hope.saas.wms.api.service.sys.SysUserMenuService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Maduo
 * @date 2020/5/22 16:36
 */
@Controller
public class LoginController {

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private RedisTemplateUtils redisTemplateUtils;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserMenuService sysUserMenuService;

    @Autowired
    private SysPostMenuService sysPostMenuService;

    @Autowired
    private LoginConfig loginConfig;

    @GetMapping("login.htm")
    @IgnoreLogin
    public String login() {
        return "login";
    }

    @PostMapping("userLogin.htm")
    @IgnoreLogin
    @ResponseBody
    public Result userLogin(String userName, String passWord, Long postId, HttpServletResponse response) throws Exception {

        if (StringUtils.isEmpty(postId)) {
            return Result.error("请选择职位！");
        }
        EntityWrapper<CustomerInfoEntity> wrapper = new EntityWrapper<>();
        wrapper.eq(CustomerInfoTable.USER_NAME, userName);
        CustomerInfoEntity entity = customerInfoService.selectOne(wrapper);
        if (entity == null) {
            throw new NewHopeException("错误的用户名或密码");
        }
        if (!MD5Utils.encryptByMD5(passWord + entity.getSalt()).equals(entity.getPassword())) {
            throw new NewHopeException("错误的用户名或密码");
        }

        String token = RandomStringUtils.randomAlphanumeric(16);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", String.valueOf(entity.getId()));
        map.put("postId", postId);
        map.put("token", token);
        String key = LoginConstant.LOGIN_KEY + entity.getId() + "." + token;
        if (loginConfig.owner) {
            redisTemplateUtils.del(key + "*");
        }
        //用户信息加密
        String aesStr = AESCodeUtil.encryptAES(JSON.toJSONString(map), loginConfig.aesKey);
        redisTemplateUtils.set(key, JSON.toJSONString(entity), loginConfig.time);
        //cookie设置为用户信息
        Cookie cookie = new Cookie(LoginConstant.LOGIN_COOKIE, aesStr);
        cookie.setPath("/");
        cookie.setMaxAge(Math.toIntExact(loginConfig.time));
        response.addCookie(cookie);
        return Result.success("登录成功");
    }

    @GetMapping("index1.htm")
    public String index1(Model model) {
        CustomerInfoEntity sysUserEntity = Constant.get().getCustomerInfoEntity();
        List<SysMenuEntity> sysMenuEntities;
        Set<String> sets = new HashSet<>();
        Set<String> buttons = new HashSet<>();
        if (sysUserEntity.getAdmin() != null && sysUserEntity.getAdmin()) {
            sysMenuEntities = sysMenuService.selectList(new EntityWrapper<>());
            sysMenuEntities = chuli(sysMenuEntities, sets, buttons);
        } else {
            List<Long> menuIds = new ArrayList<>();

            EntityWrapper wrapper = new EntityWrapper();
            wrapper.eq(SysUserMenuTable.USER_ID, Constant.get().getCustomerInfoEntity().getId());
            List<SysUserMenuEntity> sysUserMenuEntities = sysUserMenuService.selectList(wrapper);
            if (sysUserMenuEntities != null) {
                for (SysUserMenuEntity sysUserMenuEntity : sysUserMenuEntities) {
                    menuIds.add(sysUserMenuEntity.getMenuId());
                }
            }
            wrapper = new EntityWrapper();
            wrapper.eq(SysPostMenuTable.POST_ID, Constant.get().getPostId());
            List<SysPostMenuEntity> sysPostMenuEntities = sysPostMenuService.selectList(wrapper);
            if (sysPostMenuEntities != null) {
                for (SysPostMenuEntity sysPostMenuEntity : sysPostMenuEntities) {
                    menuIds.add(sysPostMenuEntity.getMenuId());
                }
            }
            sysMenuEntities = sysMenuService.selectBatchIds(menuIds);
            sysMenuEntities = chuli(sysMenuEntities, sets, buttons);
        }
        model.addAttribute("sysMenuEntities", sysMenuEntities);
        model.addAttribute("buttons", buttons);
        //不是超级管理员
        if (sysUserEntity.getAdmin() == null || !sysUserEntity.getAdmin()) {
            String key = LoginConstant.USER_AUTH_KEY + Constant.get().getToken() + "." + Constant.get().getPostId();
            redisTemplateUtils.set(key, JSON.toJSONString(sets), loginConfig.time);
        }
        model.addAttribute("username", Constant.get().getCustomerInfoEntity().getUserName());
        return "index1";
    }

    @GetMapping("main.htm")
    @IgnoreLogin
    public String main() {
        return "main";
    }

    /**
     * 根目录
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/")
    @IgnoreLogin
    public String index() throws Exception {
        if (Constant.get() == null) {
            return "redirect:/login.htm";
        }
        return "redirect:/index1.htm";
    }

    /**
     * 注销
     *
     * @return
     * @throws Exception
     */
    @GetMapping("logOut.htm")
    public String logOut() throws Exception {
        String key = LoginConstant.LOGIN_KEY + Constant.get().getCustomerInfoEntity().getId() + "." + Constant.get().getToken();
        redisTemplateUtils.del(key);
        return "redirect:/login.htm";
    }

    private List<SysMenuEntity> chuli(List<SysMenuEntity> sysMenuEntities, Set<String> sets, Set<String> buttons) {
        List<SysMenuEntity> list = new ArrayList<>();
        //取出根
        SysMenuEntity gen = null;
        Map<Long, List<SysMenuEntity>> map = new HashMap<>();
        for (SysMenuEntity sysMenuEntity : sysMenuEntities) {
            if (sysMenuEntity.getRootFlag()) {
                gen = sysMenuEntity;
            }
            Long pid = sysMenuEntity.getParent();
            List<SysMenuEntity> entities = map.get(pid);
            if (entities == null) {
                entities = new ArrayList<>();
            }
            entities.add(sysMenuEntity);
            map.put(pid, entities);

            String authorityUrl = sysMenuEntity.getAuthorityUrl();
            String authorityButton = sysMenuEntity.getAuthorityButton();
            if (!StringUtils.isEmpty(authorityUrl)) {
                String[] authorityUrls = authorityUrl.split(",");
                Collections.addAll(sets, authorityUrls);
            }
            if (!StringUtils.isEmpty(authorityButton)) {
                String[] authorityButtons = authorityButton.split(",");
                Collections.addAll(buttons, authorityButtons);
            }

        }
        for (SysMenuEntity sysMenuEntity : sysMenuEntities) {
            if (gen.getId().equals(sysMenuEntity.getParent())) {
                sysMenuEntity.setSysMenuEntities(map.get(sysMenuEntity.getId()));
                list.add(sysMenuEntity);
            }
        }
        return list;
    }
}
