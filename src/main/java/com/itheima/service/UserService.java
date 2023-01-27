package com.itheima.service;

import com.itheima.pojo.PageBean;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserService {
    /**
     * 登录
     */
    User login(User user);

    /**
     * 查询所有宿管
     */
    List<User> selectAll();

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
    User selectById(int id);

    /**
     * 根据id修改
     */
    boolean updateById(User user);

    /**
     * 分页查询
     * @param currentPage  第几页
     * @param pageSize  每页展示条数
     * @return
     */
    PageBean<User> selectBypage(int currentPage, int pageSize,User user);

    /**
     * 根据id数组进行多条删除
     */
    void deleteByIds(int[] ids);
}
