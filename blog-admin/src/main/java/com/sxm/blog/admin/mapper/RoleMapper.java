package com.sxm.blog.admin.mapper;import com.sxm.blog.admin.entity.Role;import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import org.springframework.stereotype.Repository;import java.util.List;/** * @author * @description * @date 2020/5/6 */@Repository@Mapperpublic interface RoleMapper {    int insert(Role role);    Role getById(@Param("id") Integer id);    List<Role> list();    List<Role> getByUserId(@Param("userId") Integer userId);}