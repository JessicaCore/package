package com.tyj.game.dao;

import com.tyj.game.bean.Score;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScoreDao {
//积分历史
    public int addScore(Score score);
//总分
    public int getTotalScore(int uid);
    //每个用户完成图片数量
    public int imgcount(int uid);

}
