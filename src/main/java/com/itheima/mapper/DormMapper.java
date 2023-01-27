package com.itheima.mapper;

import com.itheima.pojo.Dorm;
import com.itheima.pojo.Louyu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DormMapper {
    /**
     * 分页条件查询
     */
    List<Dorm> selectPage(@Param("begin") int begin, @Param("size") int size, @Param("dorm") Dorm dorm);
    /**
     * 条件查询总条数
     */
    int selectTotalCount(@Param("dorm") Dorm dorm);
}
