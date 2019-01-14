package com.bisa.health.shop.admin.dao;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.admin.model.SAfterForProgress;

@Repository
public class AdminAfterForProgressDaoImpl extends BaseDao<SAfterForProgress> implements IAdminAfterForProgressDao {

    @Override
    public boolean insert(SAfterForProgress progress) {
        String sql = "INSERT INTO s_after_for_progress(after_for_record_id, user_id,sys_user_id, progress_description, check_type, department_type, create_time) VALUES(?,?,?,?,?,?,?)";
        int result = super.addBySql(sql,
                new Object[]{progress.getAfter_for_record_id(), progress.getUser_id(), progress.getSys_user_id(),
                        progress.getProgress_description(), progress.getCheck_type(), progress.getDepartment_type(),
                        progress.getCreate_time()});
        return result >= 1 ? true : false;
    }

}
