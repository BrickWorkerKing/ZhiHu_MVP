package com.nn.zhihumvp.base;

/**
 * DTO到VO转换mapper
 *
 * @author LiuZongRui  16/11/22
 */

public interface IBaseMapper<T> {
    T transform();
}
