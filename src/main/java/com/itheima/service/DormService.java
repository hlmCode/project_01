package com.itheima.service;

import com.itheima.pojo.Dorm;
import com.itheima.pojo.PageBean;

public interface DormService {
    /**
     * 条件分页查询
     * */
    PageBean<Dorm> selectPage(int begin,int size,Dorm dorm);
}
