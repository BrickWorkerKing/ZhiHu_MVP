package com.nn.zhihumvp.model.dto;

import com.google.gson.annotations.SerializedName;
import com.nn.zhihumvp.base.IBaseMapper;
import com.nn.zhihumvp.model.vo.LatestNewsVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 最新新闻
 *
 * @author LiuZongRui  16/11/23
 */

public class LatestNewsDTO implements IBaseMapper<Map<String, List<LatestNewsVO>>> {


    public String date;
    public List<StoriesBean> stories;
    @SerializedName("top_stories")
    public List<TopStoriesBean> topStories;

    @Override
    public Map<String, List<LatestNewsVO>> transform() {
        Map<String, List<LatestNewsVO>> map = new HashMap<>();
        List<LatestNewsVO> newsList = new ArrayList<>();
        List<LatestNewsVO> topNewsList = new ArrayList<>();
        for (StoriesBean bean : stories) {
            LatestNewsVO vo = new LatestNewsVO();
            vo.setImage(bean.images == null ? "" : bean.images.get(0));
            vo.setTitle(bean.title == null ? "" : bean.title);
            vo.setId(bean.id == null ? "" : bean.id);
            newsList.add(vo);
        }
        for (TopStoriesBean bean : topStories) {
            LatestNewsVO vo = new LatestNewsVO();
            vo.setImage(bean.image == null ? "" : bean.image);
            vo.setTitle(bean.title == null ? "" : bean.title);
            vo.setId(bean.id == null ? "" : bean.id);
            topNewsList.add(vo);
        }
        map.put("newsList", newsList);
        map.put("banner", topNewsList);
        return map;
    }


    public static class StoriesBean {
        public String type;
        public String id;
        @SerializedName("ga_prefix")
        public String gaPrefix;
        public String title;
        public List<String> images;
    }

    public static class TopStoriesBean {
        public String image;
        public String type;
        public String id;
        @SerializedName("ga_prefix")
        public String gaPrefix;
        public String title;
    }
}
