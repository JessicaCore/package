package com.tyj.game.dao;

import com.tyj.game.bean.Label;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LabelDao {
    //添加方式
    int  add (Label label);

    //更新方法
    int update(Label label);
    int adminupdate(Label label);
    //查询label
    Label select(Label label);
//查询总数
    int findTotal();
    //管理员查询未审核总数
    int adminfindTotal(int uid);
    //管理员查询未审核详情
    List<Label> adminfindALl(int uid);

    List<Label> findALl();
    //所有任务总数
    int countTotal();
    //已操作内容总数
    int count123();
    Label find(Label label);
//    int getscore(int uid);
    //用户完成总数
    int count(int uid);
    //管理员审核总数
    int admincount(int uid);
    //打回操作数
    int count0(int uid);
    //查询正确答案

    int getCorrect(int uid);
    //埋题正确率
    List<Label> zhengquelv(int uid);
    Label findById(int id);
}
