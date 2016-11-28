package com.nn.zhihumvp.model.dto;

import com.nn.zhihumvp.base.IBaseMapper;
import com.nn.zhihumvp.model.vo.SectionVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 栏目列表传输对象
 *
 * @author LiuZongRui  16/11/23
 */

public class SectionListDTO implements IBaseMapper<List<SectionVO>> {

    public List<DataBean> data;

    @Override
    public List<SectionVO> transform() {
        List<SectionVO> list = new ArrayList<>();
        for (DataBean bean : data) {
            SectionVO vo = new SectionVO();
            vo.setId(bean.id == null ? "" : bean.id);
            vo.setName(bean.name == null ? "" : bean.name);
            vo.setDescription(bean.description == null ? "" : bean.description);
            vo.setThumbnail(bean.thumbnail == null ? "" : bean.thumbnail);
            list.add(vo);
        }
        return list;
    }

    public static class DataBean {
        public String description;
        public String id;
        public String name;
        public String thumbnail;
    }
}
