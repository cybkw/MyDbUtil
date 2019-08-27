package com.bkw.wangyidb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bkw.wangyidb.db.BaseDao;
import com.bkw.wangyidb.db.BaseDaoFactory;
import com.bkw.wangyidb.db.PhotoDao;
import com.bkw.wangyidb.db.UserDao;
import com.bkw.wangyidb.model.Photo;
import com.bkw.wangyidb.model.User;
import com.bkw.wangyidb.subdb.BaseDaoSubFactory;
import com.bkw.wangyidb.update.UpdateManager;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btInsert;

    int i = 0;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDao = BaseDaoFactory.getInstance().getBaseDao(UserDao.class, User.class);

        initUI();
    }

    private void initUI() {


        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDao baseDao = BaseDaoFactory.getInstance().getBaseDao(BaseDao.class, User.class);
                baseDao.insert(new User(1, "bkw", "111"));
                baseDao.insert(new User(2, "bkw", "111"));
                baseDao.insert(new User(3, "bkw", "111"));
                baseDao.insert(new User(4, "bkw", "111"));
            }
        });


        findViewById(R.id.select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDao baseDao = BaseDaoFactory.getInstance().getBaseDao(BaseDao.class, User.class);
                User where = new User();
                where.setPassword("111");

                List<User> list = baseDao.query(where);
                Log.e("TAG", "list size is " + list.size());
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(list.get(i).toString());
                }
            }
        });


        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDao dao = BaseDaoFactory.getInstance().getBaseDao(BaseDao.class, User.class);
                User user = new User();
                user.setName("uuc");

                User where = new User();
                where.setName("bkw");
                dao.update(user, where);
            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//
                BaseDao baseDao = BaseDaoFactory.getInstance().getBaseDao(UserDao.class, User.class);
                User where = new User();
                where.setPassword("111");
                baseDao.delete(where);

            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //服务器返回的信息
                User user = new User();
                user.setName("bkw" + (i++));
                user.setPassword("123131");
                user.setId(i);

                //数据插入
                userDao.insert(user);
                Toast.makeText(MainActivity.this, "执行成功", Toast.LENGTH_SHORT).show();

            }
        });

        findViewById(R.id.subInsert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Photo photo = new Photo();
                photo.setPath("/data/data/xxx.png");
                photo.setTime(new Date().toString());

                PhotoDao photoDao = BaseDaoSubFactory.getInstance().getBaseDao(PhotoDao.class, Photo.class);
                photoDao.insert(photo);
            }
        });

        findViewById(R.id.newVersion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateManager updateManager = new UpdateManager();
                updateManager.startUpdateDb(MainActivity.this);
            }
        });
    }


}
