package com.itheima.service.impl;

import com.itheima.mapper.DormMapper;
import com.itheima.pojo.Dorm;
import com.itheima.pojo.PageBean;
import com.itheima.service.DormService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class DormServiceimpl implements DormService {
    /**
     * 1.获取sessionFactory
     */
    SqlSessionFactory sessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    /**
     * 条件分页查询
     * */
    @Override
    public PageBean<Dorm> selectPage(int page, int size, Dorm dorm) {
        // 处理数据
        // 第几页
        int begin = (page - 1) * size;
        // 每页多少条不需要改变

        // 判断dorm的成员变量是否是空 为空则不添加 模糊查询的%符号
        if (dorm.getLname() != null && dorm.getLname().length()>0){
            dorm.setLname("%" + dorm.getLname() + "%");
        }
        if (dorm.getLbrief() != null && dorm.getLbrief().length()>0){
            dorm.setLgender("%" + dorm.getLbrief() + "%");
        }

        // 2.获取Sqlsession对象
        SqlSession sqlSession = sessionFactory.openSession();
        // 3.获取mapper对象
        DormMapper mapper = sqlSession.getMapper(DormMapper.class);
        // 4.调用方法
        List<Dorm> dorms = mapper.selectPage(begin, size, dorm);
        int totalCount = mapper.selectTotalCount(dorm);

        // 5.封装后返回数据
        PageBean<Dorm> pageBean = new PageBean<>();
        pageBean.setRows(dorms);
        pageBean.setTotalCount(totalCount);

        // 释放资源
        sqlSession.close();
        return pageBean;
    }
}
