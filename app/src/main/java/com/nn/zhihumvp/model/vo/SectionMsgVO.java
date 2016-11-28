package com.nn.zhihumvp.model.vo;

/**
 * 栏目信息表现层对象
 * 在UI上显示的数据
 *
 * @author LiuZongRui  16/11/22
 */

public class SectionMsgVO {

    private String date;
    private String title;
    private String image;
    private String id;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
