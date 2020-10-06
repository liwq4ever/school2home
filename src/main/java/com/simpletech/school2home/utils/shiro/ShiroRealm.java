package com.simpletech.school2home.utils.shiro;

import com.simpletech.school2home.dao.SysModuleMapper;
import com.simpletech.school2home.dao.SysUserMapper;
import com.simpletech.school2home.pojo.SysModule;
import com.simpletech.school2home.pojo.SysUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @项目名称：wyait-manage
 * @包名：com.wyait.manage.shiro
 * @类描述：
 * @创建人：wyait
 * @创建时间：2017-12-13 13:53
 * @version：V1.0
 */
public class ShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysModuleMapper sysModuleMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //TODO
        // 添加权限 和 角色信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取当前登陆用户
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        List<String> moudleStrs = sysModuleMapper.selectListByRoleId(sysUser.getRoleId());
        for (String moudleId : moudleStrs) {
            SysModule sysModule = sysModuleMapper.selectOneById(moudleId);
            if (sysModule != null && sysModule.getCode() != null && sysModule.getStatus() == 0) {
                authorizationInfo.addStringPermission(sysModule.getCode());
            }
        }
        return authorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String loginName = token.getUsername();
        logger.info("当前登录用户==" + loginName);
        SysUser SysUser = sysModuleMapper.selectOneByLoginName(loginName);
        if (SysUser == null) {
            // 用户不存在
            return null;
        } else {
            // 密码存在
            // 第一个参数 ，登陆后，需要在session保存数据
            // 第二个参数，查询到密码(加密规则要和自定义的HashedCredentialsMatcher中的HashAlgorithmName散列算法一致)
            // 第三个参数 ，realm名字
            String name = DigestUtils.md5Hex(SysUser.getId() + SysUser.getLoginName());
            return new SimpleAuthenticationInfo(SysUser, DigestUtils.md5Hex(SysUser.getPassword()), name);
        }
    }

    public void clearAuthz() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
