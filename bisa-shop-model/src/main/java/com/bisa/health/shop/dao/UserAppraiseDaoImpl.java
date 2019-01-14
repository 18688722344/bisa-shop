package com.bisa.health.shop.dao;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.UserAppraise;

@Repository
public class UserAppraiseDaoImpl extends BaseDao<UserAppraise> implements IUserAppraiseDao {

    @Override
    public Pager<UserAppraise> getPagerAppraiseByGoodsNumber(String goodsNumber) {
        String sql = "select * from s_user_appraise WHERE goods_number = ?";
        return super.findBySql(sql, new Object[]{goodsNumber}, UserAppraise.class, true);
    }

    @Override
    public Pager<UserAppraise> getPagerAppraiseByUserGuid(Integer userGuid) {
        String sql = "select * from s_user_appraise WHERE user_guid = ?";
        return super.findBySql(sql, new Object[]{userGuid}, UserAppraise.class, true);
    }

    @Override
    public int addUserAppraise(UserAppraise userAppraise) {
        String sql = "INSERT INTO s_user_appraise(user_guid, goods_number, username, user_avator, appraise_one, appraise_two, appraise_degree,"
                + " appraise_one_time, appraise_two_time, reply, reply_user_guid, reply_time)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return super.addBySql(sql, new Object[]{userAppraise.getUserGuid(), userAppraise.getGoodsNumber(), userAppraise.getUsername(), userAppraise.getUserAvator(), userAppraise.getAppraiseOne(), userAppraise.getAppraiseTwo(), userAppraise.getAppraiseDegree(),
                userAppraise.getAppraiseOneTime(), userAppraise.getAppraiseTwoTime(), userAppraise.getReply(), userAppraise.getReplyUserGuid(), userAppraise.getReplyTime()});
    }

    @Override
    public int updateUserAppraise(UserAppraise userAppraise) {
        String sql = "UPDATE s_user_appraise SET appraise_two = ?, appraise_two_time = ?, reply = ?, reply_user_guid = ?, reply_time = ? WHERE id = ?";
        return super.updateBySql(sql, new Object[]{userAppraise.getAppraiseTwo(), userAppraise.getAppraiseTwoTime(), userAppraise.getReply(), userAppraise.getReplyUserGuid(), userAppraise.getReplyTime(), userAppraise.getId()});
    }

    @Override
    public UserAppraise getAppraiseById(Integer id) {
        String sql = "select * from s_user_appraise WHERE id = ?";
        return super.selectObjectBySql(sql, new Object[]{id}, UserAppraise.class);
    }

}
