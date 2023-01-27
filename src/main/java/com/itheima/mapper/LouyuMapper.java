package com.itheima.mapper;

import com.itheima.pojo.Louyu;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LouyuMapper {
    /**
     * 分页条件查询
     */
    List<Louyu> selectPage(@Param("begin") int begin,@Param("size") int size,@Param("louyu") Louyu louyu);
    /**
     * 条件查询总条数
     */
    int selectTotalCount(@Param("louyu") Louyu louyu);

    /**
     * 得到所有宿管的名字和id
     */
    List<User> selectByUser();

    /**
     * 新增楼宇
     */
    void add(Louyu louyu);

    /**
     * 根据id删除
     */
    @Delete("delete from louyu where id = #{id};")
    void deleteById(int id);

    /**
     * 根据id查询
     */
    @Select("select * from louyu where id = #{id};")
    @ResultMap("LouyuResultMap")
    Louyu selectById(int id);

    /**
     * 根据id进行修改
     */
    void updateId(Louyu louyu);

    /**
     * 根据楼宇编号查询，是否有重复
     */
    Louyu selectByName(Louyu louyu);
}
