package com.nn.zhihumvp.model.dto;

import com.nn.zhihumvp.base.IBaseMapper;
import com.nn.zhihumvp.model.vo.StartImageVO;

/**
 * 欢迎页面信息
 *
 * @author LiuZongRui  16/11/14
 */

public class StartImageDTO implements IBaseMapper<StartImageVO> {

    public String text;
    public String img;

    @Override
    public StartImageVO transform() {
        StartImageVO vo = new StartImageVO();
        vo.setImg(img == null ? "" : img);
        return vo;
    }
}
