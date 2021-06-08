package com.yzm.mapper;

import com.yzm.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: yzm
 * @Date: 2021/6/8 - 06 - 08 - 17:23
 */
@Mapper
public interface ProductMapper {
    public Product findProductById(Integer id);
}
