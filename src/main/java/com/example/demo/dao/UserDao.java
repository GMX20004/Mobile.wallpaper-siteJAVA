package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.dto.UserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 用户
 */
@Mapper
public interface UserDao extends BaseMapper<UserDTO> {
    //登录判断
    //mysql8.0及以上不支持PASSWORD函数,需要用SHA1函数代替
    @Select(" SELECT *  FROM `user` WHERE email = #{email} and `password`=SHA1(#{password})")
    List<UserDTO> getLogInToCode(Map<String,Object> param);
    //判断是否已存在用户
    @Select(" SELECT COUNT(*) AS num FROM `user` WHERE email = #{email}")
    int getEmailCode(Map<String,Object> param);
    //查询用户信息
    @Select("SELECT * FROM `user` WHERE id=#{id}")
    List<UserDTO> getUserCode(Map<String,Object> param);
    //新建用户
    @Insert("INSERT INTO `user`(email,`password`,user_id,creation_time,width,height)VALUES(#{email},SHA1(#{password}),#{uuid},#{time},#{width},#{height})")
    int getRegisteredCode(Map<String,Object> param);
    //用户修改密码
    @Update("UPDATE `user` SET `password` = SHA1(#{password}) WHERE email = #{email}")
    int getModifyCode(Map<String,Object> param);
    //查询所有用户信息--管理员权限
    @Select("SELECT * FROM `user`")
    List<UserDTO> userViewCode();
    //删除用户--管理员权限
    @Delete("DELETE FROM `user` WHERE id=#{id}")
    int deleteUserCode(Map<String,Object> param);
    //更改用户状态--管理员权限
    @Update("UPDATE `user` SET state=#{state} WHERE id=#{id}")
    int stateUserCode(Map<String,Object> param);
    //更新最近登入时间
    @Update("UPDATE `user` SET recent_login=#{time} WHERE email=#{email}")
    int userTimeCode(Map<String,Object> param);
    //用户收藏点赞总数修改
    void userAddDeleteCGCode(Map<String,Object> param);
    //用户信息修改
    void userModifyCode(Map<String,Object> param);
    //用户投稿增加
    @Update("UPDATE `user` SET contribute=contribute+1 WHERE id = #{userId}")
    int userContributeCode(int userId);
    //修改用户唯一编码
    @Update("UPDATE `user` SET user_id=#{uuid} WHERE id=#{id}")
    int userUpdateUuidCode(Map<String,Object> param);
    //查看用户唯一编码
    @Select("SELECT COUNT(*) FROM `user` WHERE email=#{email} AND user_id=#{uuid}")
    int userUuidCode(Map<String,Object> param);
}
