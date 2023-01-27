package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    /**
     * 根据类型进行登录
     */
    User login(User user);

    /**
     * 查询所有宿管
     */
    List<User> selectAll();

    /**
     * 查询用户名是否存在
     */
    User selectByuserName(User user);

    /**
     * 新增宿管
     */
    boolean add(User user);

    /**
     * 根据id删除
     */
    void deleteById(int id);

    /**
     * 根据id查询
     */
    @Select("select * from user where id = #{id};")
    @ResultMap("userResultMap")
    User selectById(int id);

    /**
     * 根据id修改
     */
    boolean updateById(User user);

    /**
     * 分页查询
     */
    List<User> selectPage(@Param("begin") int begin, @Param("size") int size, @Param("user") User user);

    /**
     * 查询总记录数
     */
    int selectTotalCount(@Param("user")User user);

    /**
     * 根据id数组进行多条删除
     */
    void deleteByIds(@Param("ids") int[] ids);
}
