package com.yzm.controller;

import com.yzm.pojo.Product;
import com.yzm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: yzm
 * @Date: 2021/6/8 - 06 - 08 - 17:34
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/show")
    public String show(Integer id, Model model){

        Product product = productService.findProductById(id);
        model.addAttribute("product",product);

        return "show";
    }

}
