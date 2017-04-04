/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/micromoving/bcp">bcp</a> All rights reserved.
 */
package cn.micromoving.bcp.modules.sys.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import cn.micromoving.bcp.common.service.BaseService;
import cn.micromoving.bcp.common.utils.CacheUtils;
import cn.micromoving.bcp.common.utils.SpringContextHolder;
import cn.micromoving.bcp.modules.sys.dao.AreaDao;
import cn.micromoving.bcp.modules.sys.dao.MenuDao;
import cn.micromoving.bcp.modules.sys.dao.OfficeDao;
import cn.micromoving.bcp.modules.sys.dao.RoleDao;
import cn.micromoving.bcp.modules.sys.dao.UserDao;
import cn.micromoving.bcp.modules.sys.entity.Area;
import cn.micromoving.bcp.modules.sys.entity.Menu;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.Role;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.security.SystemAuthorizingRealm.Principal;
import cn.micromoving.bcp.modules.sys.service.SystemService;

/**
 * 用户工具类
 * 
 * @author songcm
 * @version 2013-12-05
 */
public class UserUtils {

    private static SystemService systemService = SpringContextHolder.getBean(SystemService.class);

    private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);

    private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);

    private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);

    private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);

    private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);

    public static final String USER_CACHE = "userCache";

    public static final String USER_CACHE_ID_ = "id_";

    public static final String USER_CACHE_LOGIN_NAME_ = "ln";

    public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";

    public static final String CACHE_ROLE_LIST = "roleList";

    public static final String CACHE_MENU_LIST = "menuList";

    public static final String CACHE_AREA_LIST = "areaList";

    public static final String CACHE_OFFICE_LIST = "officeList";

    public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";

    public static final double DEFAULT_WEIGHT = 390;

    public static final double DEFAULT_HEIGHT = 570;

    public static final int profile_photo_width = 250;

    public static final int profile_photo_height = 250;

    /**
     * 根据ID获取用户
     * 
     * @param id
     * @return 取不到返回null
     */
    public static User get(String id) {
        User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
        if (user == null) {
            user = userDao.get(id);
            if (user == null) {
                return null;
            }
            user.setRoleList(roleDao.findList(new Role(user)));
            CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
            CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
        }
        return user;
    }

    /**
     * 根据登录名获取用户
     * 
     * @param loginName
     * @return 取不到返回null
     */
    public static User getByLoginName(String loginName) {
        User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
        if (user == null) {
            user = userDao.getByLoginName(new User(null, loginName));
            if (user == null) {
                return null;
            }
            user.setRoleList(roleDao.findList(new Role(user)));
            CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
            CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
        }
        return user;
    }

    /**
     * 清除当前用户缓存
     */
    public static void clearCache() {
        removeCache(CACHE_ROLE_LIST);
        removeCache(CACHE_MENU_LIST);
        removeCache(CACHE_AREA_LIST);
        removeCache(CACHE_OFFICE_LIST);
        removeCache(CACHE_OFFICE_ALL_LIST);
        UserUtils.clearCache(getUser());
    }

    /**
     * 清除指定用户缓存
     * 
     * @param user
     */
    public static void clearCache(User user) {
        CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
        CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName());
        CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getOldLoginName());
        if (user.getOffice() != null && user.getOffice().getId() != null) {
            CacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_OFFICE_ID_ + user.getOffice().getId());
        }
    }

    /**
     * 获取当前用户
     * 
     * @return 取不到返回 new User()
     */
    public static User getUser() {
        Principal principal = getPrincipal();
        if (principal != null) {
            User user = get(principal.getId());
            if (user != null) {
                return user;
            }
            return new User();
        }
        // 如果没有登录，则返回实例化空的User对象。
        return new User();
    }

    /**
     * 获取当前用户角色列表
     * 
     * @return
     */
    public static List<Role> getRoleList() {
        @SuppressWarnings("unchecked")
        List<Role> roleList = (List<Role>) getCache(CACHE_ROLE_LIST);
        if (roleList == null) {
            User user = getUser();
            if (user.isAdmin()) {
                roleList = roleDao.findAllList(new Role());
            } else {
                Role role = new Role();
                role.getSqlMap().put("dsf", BaseService.dataScopeFilter(user.getCurrentUser(), "o", "u"));
                roleList = roleDao.findList(role);
            }
            putCache(CACHE_ROLE_LIST, roleList);
        }
        return roleList;
    }

    /**
     * 获取当前用户授权菜单
     * 
     * @return
     */
    public static List<Menu> getMenuList() {
        @SuppressWarnings("unchecked")
        List<Menu> menuList = (List<Menu>) getCache(CACHE_MENU_LIST);
        if (menuList == null) {
            User user = getUser();
            if (user.isAdmin()) {
                menuList = menuDao.findAllList(new Menu());
            } else {
                Menu m = new Menu();
                m.setUserId(user.getId());
                menuList = menuDao.findByUserId(m);
            }
            putCache(CACHE_MENU_LIST, menuList);
        }
        return menuList;
    }

    /**
     * 获取当前用户授权菜单
     * 
     * @return
     */
    public static List<Menu> getMenuListByParentId(String parentId) {

        Menu parentMenu = new Menu();
        parentMenu.setId(parentId);
        @SuppressWarnings("unchecked")
        List<Menu> menuList = (List<Menu>) getCache(CACHE_MENU_LIST + parentId);
        if (menuList == null) {
            User user = getUser();
            Menu m = new Menu();
            m.setParent(parentMenu);
            if (user.isAdmin()) {
                menuList = menuDao.findAllList(m);
            } else {

                m.setUserId(user.getId());
                menuList = menuDao.findByUserId(m);
            }
            putCache(CACHE_MENU_LIST + parentId, menuList);
        }
        return menuList;
    }

    /**
     * 获取当前用户授权的区域
     * 
     * @return
     */
    public static List<Area> getAreaList() {
        @SuppressWarnings("unchecked")
        List<Area> areaList = (List<Area>) getCache(CACHE_AREA_LIST);
        if (areaList == null) {
            areaList = areaDao.findAllList(new Area());
            putCache(CACHE_AREA_LIST, areaList);
        }
        return areaList;
    }

    /**
     * 根据部门编号，取得部门信息。
     * 
     * @param id 部门编号
     * @return 部门编号对应的部门信息。
     */
    public static Office getOfficeById(String id) {
        return officeDao.get(id);
    }

    /**
     * 通过当前部门ID，得到从部门名称的全路径。其中根OFFICE不显示。
     * 
     * @param currOfficeId 当前部门编号
     * @return
     */
    public static String getOfficeRootName(String currOfficeId) {

        StringBuffer officePath = new StringBuffer();
        if (StringUtils.isBlank( currOfficeId)) {
            return "";
        } else {
            Office currOffice = officeDao.get(currOfficeId);
            String parentIds = currOffice.getParentIds();
            String[] parentId = parentIds.split(",");
            for (int i = 2; i < parentId.length; i++) {

                officePath.append(officeDao.get(parentId[i]).getName());
                officePath.append(">");
            }
            officePath.append(currOffice.getName());
        }

        return officePath.toString();

    }

    /**
     * 获取当前用户有权限访问的部门
     * 
     * @return
     */
    public static List<Office> getOfficeList() {
        @SuppressWarnings("unchecked")
        List<Office> officeList = (List<Office>) getCache(CACHE_OFFICE_LIST);
        if (officeList == null) {
            User user = getUser();
            if (user.isAdmin()) {
                officeList = officeDao.findAllList(new Office());
            } else {
                Office office = new Office();
                office.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
                officeList = officeDao.findList(office);
            }
            putCache(CACHE_OFFICE_LIST, officeList);
        }
        return officeList;
    }

    /**
     * 获取当前用户有权限访问的部门
     * 
     * @return
     */
    public static List<Office> getOfficeAllList() {
        @SuppressWarnings("unchecked")
        List<Office> officeList = (List<Office>) getCache(CACHE_OFFICE_ALL_LIST);
        if (officeList == null) {
            officeList = officeDao.findAllList(new Office());
        }
        return officeList;
    }

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录者对象
     */
    public static Principal getPrincipal() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return principal;
            }
            // subject.logout();
        } catch (UnavailableSecurityManagerException e) {

        } catch (InvalidSessionException e) {

        }
        return null;
    }

    public static Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                session = subject.getSession();
            }
            if (session != null) {
                return session;
            }
            // subject.logout();
        } catch (InvalidSessionException e) {

        }
        return null;
    }

    // ============== User Cache ==============

    public static Object getCache(String key) {
        return getCache(key, null);
    }

    public static Object getCache(String key, Object defaultValue) {
        // Object obj = getCacheMap().get(key);
        Object obj = getSession().getAttribute(key);
        return obj == null ? defaultValue : obj;
    }

    public static void putCache(String key, Object value) {
        // getCacheMap().put(key, value);
        getSession().setAttribute(key, value);
    }

    public static void removeCache(String key) {
        // getCacheMap().remove(key);
        getSession().removeAttribute(key);
    }

    // public static Map<String, Object> getCacheMap(){
    // Principal principal = getPrincipal();
    // if(principal!=null){
    // return principal.getCacheMap();
    // }
    // return new HashMap<String, Object>();
    // }

    /**
     * 验证登录名是否有效
     * 
     * @param oldLoginName
     * @param loginName
     * @return
     */
    public static String checkLoginName(String oldLoginName, String loginName) {
        /* 若当前名称不为空且与原登录名相等则返回true */
        if (loginName != null && loginName.equals(oldLoginName)) {
            return "true";
        }
        /* 若当前登录名不为空，且该名称对应的用户为空则返回true */
        else if (loginName != null && systemService.getUserByLoginName(loginName) == null) {
            return "true";
        }
        return "false";
    }

   
}
