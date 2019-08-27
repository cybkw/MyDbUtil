package com.bkw.wangyidb.model;

import com.bkw.wangyidb.annotation.DbField;
import com.bkw.wangyidb.annotation.DbTable;

/**
 * 得到User对应表名
 *
 * @author bkw
 */
@DbTable("tb_user")
public class User {

    /*
     * 得到User对象对应列名
     * 第二种方式：
     * Field("u_id")
     * private Integer id;
     * private String name;
     */
    @DbField("u_id")
    private Integer id;
    @DbField("name")
    private String name;
    @DbField("password")
    private String password;
    private Integer status;


    public User() {
    }

    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
