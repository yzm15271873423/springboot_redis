package com.yzm.service.impl;

import com.yzm.mapper.ProductMapper;
import com.yzm.pojo.Product;
import com.yzm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * @Auther: yzm
 * @Date: 2021/6/8 - 06 - 08 - 17:33
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Product findProductById(Integer id) {
        String key = "product:"+id;
        //先从Redis中取出数据
        if(redisTemplate.hasKey(key)){
            System.out.println("执行缓存");
            redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Product>(Product.class));
            Product product = (Product)redisTemplate.opsForValue().get(key);
            return product;
        }

        System.out.println("执行sql");
        Product product = productMapper.findProductById(id);
        redisTemplate.opsForValue().set(key,product);
        return product;
    }
}
