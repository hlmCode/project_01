package com.itheima.service.impl;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserServiceimpl implements UserService {
    /**
     * 1.获取sqlSessionFactory对象
     */
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    /**
     *登录
     */
    @Override
    public User login(User user) {
        // 2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获得mapper对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 4.调用方法
        User login = mapper.login(user);
        // 释放资源
        sqlSession.close();
        // 返回结果
        return login;
    }

    /**
     * 查询所有宿管
     */
    @Override
    public List<User> selectAll() {
        // 2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获得mapper对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 4.调用方法
        List<User> users = mapper.selectAll();
        // 释放资源
        sqlSession.close();
        // 返回结果
        return users;
    }


    /**
     * 新增宿管
     */
    @Override
    public boolean add(User user) {
        // 2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获得mapper对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 4.调用方法
            // 4.1判断此用户名是否存在
        User u = mapper.selectByuserName(user);
        if (u != null){
            // 存在则返回false添加失败
            sqlSession.close();
            return false;
        }
        // 不存在则可以添加
        mapper.add(user);
        // 5.提交事务
        sqlSession.commit();
        // 6.释放资源
        sqlSession.close();
        // 返回true成功
        return true;
    }

    /**
     * 根据id删除
     */
    @Override
    public void deleteById(int id) {
        // 2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获得mapper对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
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
    public User selectById(int id) {
        // 2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获得mapper对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 4.调用方法
        User user = mapper.selectById(id);
        // 5.释放资源
        sqlSession.close();
        // 6.返回结果
        return user;
    }

    /**
     * 根据id修改
     */
    @Override
    public boolean updateById(User user) {
        // 2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获得mapper对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 4.调用方法  如果我修改了用户名字 这个时候需要去判断一下  名字是否和其他的用户名字相同
        User u = mapper.selectByuserName(user);
        if ( u != null && user.getId() != u.getId()){
            sqlSession.close();
            return false;
        }

        mapper.updateById(user);
        // 5.提交事务
        sqlSession.commit();
        // 6.释放资源
        sqlSession.close();
        return true;
    }

    /**
     * 分页查询
     * @param currentPage  第几页
     * @param pageSize  每页展示条数
     * @return
     */
    @Override
    public PageBean<User> selectBypage(int currentPage, int pageSize, User user) {
        // 2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获得mapper对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 4.调用方法
        if (user.getName() != null && user.getName().length() > 0){
            user.setName("%" + user.getName() + "%");
        }
        if (user.getPhone() != null && user.getPhone().length() > 0){
            user.setPhone("%" + user.getPhone() + "%");
        }
        List<User> users = mapper.selectPage(currentPage, pageSize,user);
        int totalCount = mapper.selectTotalCount(user);
        // 封装到PageBean中
        PageBean<User> userPageBean = new PageBean<>();
        userPageBean.setRows(users);
        userPageBean.setTotalCount(totalCount);

        // 5，释放资源
        sqlSession.close();
        // 6.返回结果
        return userPageBean;
    }

    /**
     * 根据id数组进行多条删除
     */
    @Override
    public void deleteByIds(int[] ids) {
        // 2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获得mapper对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 4.调用方法
        mapper.deleteByIds(ids);
        // 5.提交事务
        sqlSession.commit();
        // 6.释放资源
        sqlSession.close();
    }
}
