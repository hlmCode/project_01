package com.itheima.service;

import com.itheima.pojo.Louyu;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface LouyuService {
    /**
     * 分页条件查询
     */
    PageBean<Louyu> selectPage(int begin, int size, Louyu louyu);

    /**
     * 得到所有宿管的名字和id
     */
    List<User> selectByUser();

    /**
     * 新增楼宇
     */
    boolean add(Louyu louyu);

    /**
     * 根据id删除
     */
    void deleteById(int id);

    /**
     * 根据id查询
     */
    Louyu selectById(int id);

    /**
     * 根据id进行修改
     */
    boolean updateId(Louyu louyu);
}
