package com.nn.zhihumvp.model.dto;

import com.google.gson.annotations.SerializedName;
import com.nn.zhihumvp.base.IBaseMapper;
import com.nn.zhihumvp.model.vo.SectionMsgVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 栏目信息的数据传输对象
 * 跟服务层返回的数据匹配
 *
 * @author LiuZongRui  16/11/22
 */

public class SectionMsgListDTO implements IBaseMapper<List<SectionMsgVO>> {

    public int timestamp;
    public String name;
    public List<StoryDTO> stories;

    @Override
    public List<SectionMsgVO> transform() {
        List<SectionMsgVO> list = new ArrayList<>();
        for (StoryDTO dto : stories) {
            list.add(dto.transform());
        }
        return list;
    }

    private static class StoryDTO implements IBaseMapper<SectionMsgVO> {

        public String date;
        @SerializedName("display_date")
        public String displayDate;
        public String id;
        public String title;
        public List<String> images;

        @Override
        public SectionMsgVO transform() {
            SectionMsgVO vo = new SectionMsgVO();
            vo.setDate(displayDate == null ? "XX月XX日" : displayDate);
            vo.setImage(images == null ? "" : images.get(0));
            vo.setTitle(title == null ? "" : title);
            vo.setId(id == null ? "0" : id);
            return vo;
        }
    }
}
