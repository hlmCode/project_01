package com.itheima.service.impl;

import com.itheima.mapper.LouyuMapper;
import com.itheima.pojo.Louyu;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.User;
import com.itheima.service.LouyuService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class LouyuServiceimpl implements LouyuService {
    // 1.获取SqlSessionFactory对象
    SqlSessionFactory sessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 分页条件查询
     */
    @Override
    public PageBean<Louyu> selectPage(int begin, int size, Louyu louyu) {
        // 2.获取sqlsession对象
        SqlSession sqlSession = sessionFactory.openSession();
        // 3.获得mapper对象
        LouyuMapper mapper = sqlSession.getMapper(LouyuMapper.class);
        // 4.调用方法
            // 将数据进行处理
        if (louyu.getName() != null && louyu.getName().length() > 0){
            louyu.setName("%" + louyu.getName() + "%");
        }
        if (louyu.getBrief() != null && louyu.getBrief().length() > 0){
            louyu.setBrief("%" + louyu.getBrief() + "%");
        }

        List<Louyu> louyus = mapper.selectPage(begin, size, louyu);
        int totalCount = mapper.selectTotalCount(louyu);

        // 封装
        PageBean<Louyu> pageBean = new PageBean<>();
        pageBean.setRows(louyus);
        pageBean.setTotalCount(totalCount);

        return pageBean;
    }

    /**
     * 得到所有宿管的名字和id
     */
    @Override
    public List<User> selectByUser() {
        // 2.获取sqlsession对象
        SqlSession sqlSession = sessionFactory.openSession();
        // 3.获取mapper对象
        LouyuMapper mapper = sqlSession.getMapper(LouyuMapper.class);
        // 4.调用方法
        List<User> users = mapper.selectByUser();
        // 5.释放资源
        return users;
    }

    /**
     * 新增楼宇
     */
    @Override
    public boolean add(Louyu louyu) {
        // 2.获取sqlsession对象
        SqlSession sqlSession = sessionFactory.openSession();
        // 3.获取mapper对象
        LouyuMapper mapper = sqlSession.getMapper(LouyuMapper.class);
        // 4.调用方法
        Louyu flag = mapper.selectByName(louyu);
        if (flag != null){
            sqlSession.close();
            return false;
        }
        mapper.add(louyu);
        // 5.提交事务
        sqlSession.commit();
        // 6.释放资源
        sqlSession.close();

        return true;
    }

    /**
     * 根据id删除
     */
    @Override
    public void deleteById(int id) {
        // 2.获取sqlsession对象
        SqlSession sqlSession = sessionFactory.openSession();
        // 3.获取mapper对象
        LouyuMapper mapper = sqlSession.getMapper(LouyuMapper.class);
        // 4.调用方法
        mapper.deleteById(id);
        // 5.提交事务
        sqlSession.commit();
        // 6.释放资源
        sqlSession.close();
    }

    /**
     * 根据id查询
     */
    @Override
    public Louyu selectById(int id) {
        // 2.获取sqlsession对象
        SqlSession sqlSession = sessionFactory.openSession();
        // 3.获取mapper对象
        LouyuMapper mapper = sqlSession.getMapper(LouyuMapper.class);
        // 4.调用方法
        Louyu louyu = mapper.selectById(id);
        // 5.释放资源
        return louyu;
    }

    /**
     * 根据id进行修改
     */
    @Override
    public boolean updateId(Louyu louyu) {
        // 2.获取sqlsession对象
        SqlSession sqlSession = sessionFactory.openSession();
        // 3.获取mapper对象
        LouyuMapper mapper = sqlSession.getMapper(LouyuMapper.class);
        // 4.调用方法
        Louyu flag = mapper.selectByName(louyu);
        if (flag != null && flag.getId() != louyu.getId() ){
            sqlSession.close();
            return false;
        }
        mapper.updateId(louyu);
        // 5.提交事务
        sqlSession.commit();
        // 6.释放资源
        sqlSession.close();

        return true;
    }
}
