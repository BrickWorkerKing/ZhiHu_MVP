package com.nn.zhihumvp.model.vo;

/**
 * 栏目信息
 *
 * @author LiuZongRui  16/11/23
 */

public class SectionVO {

    private String thumbnail; // 栏目压缩图
    private String id;
    private String name; // 栏目名称
    private String description; // 说明

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
