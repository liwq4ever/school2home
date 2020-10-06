package com.simpletech.school2home.dao;

import com.simpletech.school2home.pojo.SysModule;
import com.simpletech.school2home.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysModuleMapper {
    SysModule selectOneById(@Param("id") String id);

    List<String> selectListByRoleId(@Param("roleId") String roleId);

    SysUser selectOneByLoginName(@Param("loginName") String loginName);

    List<SysModule> selectList(SysModule sysModule);
}
