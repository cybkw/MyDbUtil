package com.bkw.wangyidb.model;

import com.bkw.wangyidb.annotation.DbTable;

@DbTable("tb_photo")
public class Photo {
    private String time;
    private String path;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 前提是必须知道有哪些表
     * 1.先把数据表进行一个备份，或者说重命名 tb_photo to_photo_bak
     * 2.重新创建后要将tb_photo表的数据导入到新建的tb_photo
     * 3.将之前备份的表 删除掉 tb_photo_bak
     *
     * */
}
