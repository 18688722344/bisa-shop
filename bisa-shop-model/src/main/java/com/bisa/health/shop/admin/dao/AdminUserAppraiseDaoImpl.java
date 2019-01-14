package com.bisa.health.shop.admin.dao;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.UserAppraise;

@Repository
public class AdminUserAppraiseDaoImpl extends BaseDao<UserAppraise> implements IAdminUserAppraiseDao {

    @Override
    public Pager<UserAppraise> getAllUserAppraise() {
        String sql = "SELECT * FROM s_user_appraise";
        return super.findBySql(sql, new Object[]{}, UserAppraise.class, true);
    }

    @Override
    public int updateReplyComment(UserAppraise userAppraise) {
        String sql = "UPDATE s_user_appraise SET reply = ?,reply_user_guid = ?,reply_time = ? WHERE id = ?";
        return super.updateBySql(sql, new Object[]{userAppraise.getReply(), userAppraise.getReplyUserGuid(), userAppraise.getReplyTime(),
                userAppraise.getId()});
    }

    @Override
    public Pager<UserAppraise> selectUserAppraiseListByGoodsNumber(String incontent) {
        String sql = "SELECT * FROM s_user_appraise where goods_number = ?";
        return super.findBySql(sql, new Object[]{incontent}, UserAppraise.class, true);
    }

    @Override
    public Pager<UserAppraise> selectUserAppraiseListByYear(String incontent) {
        String sql = "SELECT * FROM s_user_appraise where date_format(appraise_one_time,'%Y') = ?";
        return super.findBySql(sql, new Object[]{incontent}, UserAppraise.class, true);
    }

    @Override
    public Pager<UserAppraise> selectUserAppraiseListByYearMonth(String incontent) {
        String sql = "SELECT * FROM s_user_appraise where date_format(appraise_one_time,'%Y-%m') = ?";
        return super.findBySql(sql, new Object[]{incontent}, UserAppraise.class, true);
    }

    @Override
    public Pager<UserAppraise> selectUserAppraiseListByYearMonthDay(String incontent) {
        String sql = "SELECT * FROM s_user_appraise where date_format(appraise_one_time,'%Y-%m-%d') = ?";
        return super.findBySql(sql, new Object[]{incontent}, UserAppraise.class, true);
    }

    @Override
    public Pager<UserAppraise> getUserAppraise(int value) {
        String sql = "SELECT * FROM s_user_appraise where appraise_degree = ?";
        return super.findBySql(sql, new Object[]{value}, UserAppraise.class, true);
    }

    @Override
    public Pager<UserAppraise> getUserAppraiseByReplied() {
        String sql = "SELECT * FROM s_user_appraise where reply is not null";
        return super.findBySql(sql, null, UserAppraise.class, true);
    }

    @Override
    public Pager<UserAppraise> getUserAppraiseByNoReply() {
        String sql = "SELECT * FROM s_user_appraise where reply is null";
        return super.findBySql(sql, null, UserAppraise.class, true);
    }

}
