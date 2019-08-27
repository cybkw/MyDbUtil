package com.bkw.wangyidb.subdb;

import com.bkw.wangyidb.db.BaseDaoFactory;
import com.bkw.wangyidb.db.UserDao;
import com.bkw.wangyidb.model.User;

import java.io.File;

/**
 * 用来产生私有数据库存放的位置
 *
 * @author bkw
 */
public enum PrivateDatabaseEnums {

    database("");

    private String value;

    PrivateDatabaseEnums(String value) {

    }

    public String getValue() {
        UserDao userDao = BaseDaoFactory.getInstance().getBaseDao(UserDao.class, User.class);
        if (userDao != null) {
            User curUser = userDao.getCurrentUser();
            if (curUser != null) {
                File file = new File("data/data/" + BaseDaoFactory.PACKAGENAME);
                if (!file.exists()) {
                    file.mkdirs();
                }
                return file.getAbsolutePath() + "/u_" + curUser.getId() + "_private.db";
            }
        }
        return null;
    }

}
