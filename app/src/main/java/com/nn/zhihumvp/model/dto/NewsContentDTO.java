package com.nn.zhihumvp.model.dto;

import com.google.gson.annotations.SerializedName;
import com.nn.zhihumvp.base.IBaseMapper;
import com.nn.zhihumvp.model.vo.NewsContentVO;

import java.util.List;

/**
 * 新闻内容DTO
 *
 * @author LiuZongRui  16/11/23
 */

public class NewsContentDTO implements IBaseMapper<NewsContentVO> {

    public String body;
    @SerializedName("image_source")
    public String imageSource;
    public String title;
    public String image;
    @SerializedName("share_url")
    public String shareUrl;
    @SerializedName("ga_prefix")
    public String gaPrefix;
    public String type;
    public String id;
    public List<?> js;
    public List<String> images;
    public List<String> css;

    @Override
    public NewsContentVO transform() {
        NewsContentVO vo = new NewsContentVO();
        vo.setCss(css == null ? "" : css.get(0));
        vo.setHtml(body == null ? "" : body);
        vo.setTitle(title == null ? "" : title);
        vo.setImage(image == null ? "" : image);
        return vo;
    }
}
