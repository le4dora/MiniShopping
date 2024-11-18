package com.test.controller;


import com.test.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.test.pojo.PageBen;
import com.test.pojo.Product;
import com.test.pojo.Result;
import com.test.service.ProductService;

import java.util.List;
import java.util.Map;

import static java.time.LocalTime.now;

@RestController
@RequestMapping("/product")
@CrossOrigin()
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Result add(@RequestBody @Validated(Product.Add.class) Product product) {//添加商品
        productService.add(product);
        return Result.success("当前时间： "+now()+" 来自于 "+"MiniShopping ");
    }

    @GetMapping
    public Result<List<Product>> list(){//获取商家商品列表
        System.out.println(productService.list());
        return Result.success(productService.list());
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {//获取商品详细信息
        Product product=productService.findById(id);
        return Result.success(product);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Product.Update.class) Product product) {//更新商品信息
        productService.update(product);
        return Result.success("当前时间： "+now()+" 来自于 "+"MiniShopping ");
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id) {//删除商品
        productService.delete(id);
        return Result.success("当前时间： "+now()+" 来自于 "+"MiniShopping ");
    }

    @PatchMapping("updateState")
    public Result updatePassword(@RequestParam @Validated(Product.UpdateState.class) String state,
                                 @RequestParam @Validated(Product.UpdateState.class)Integer id) {//更新商品状态
        productService.updateState(state,id);
        return Result.success("当前时间： "+now()+" 来自于 "+"MiniShopping ");
    }

    @GetMapping("/search")
    public Result<PageBen<Product>> Search(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer store_id,
            @RequestParam(required = false) Integer consumer_id,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer min_price,
            @RequestParam(required = false) Integer max_price,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String state
            ) {
        PageBen<Product> pageBen=productService.search( pageNum,  pageSize,  name,  store_id,consumer_id, description, min_price, max_price, category,  state);
        return Result.success(pageBen);
    }

    @PutMapping("/buy")
    public Result buy(@RequestParam Integer id) {
        Product product=productService.findById(id);
        if(product.getConsumer_id()!=0 || !product.getState().equals("出售中")) {
            return Result.error("商品状态异常"+product);
        }
        //获取id
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer user_id=(Integer) map.get("id");
        if(product.getStore_id()==user_id){
            return Result.error("商家与消费者不能为同一人");
        }
        productService.buy(id,user_id);
        return Result.success("当前时间： "+now()+" 来自于 "+"MiniShopping ");
    }


}



//    @PutMapping("sale")
//    public Result sale(@RequestParam Integer id,@RequestParam Integer amount){//上架商品
//        Product product=productService.findById(id);
//        if(product==null){
//            return Result.error("找不到该商品");
//        }
//        if(product.getAmount()<amount){
//            return Result.error("仓库中该商品数量不足·");
//        }
//        Product copy=Product.copy(product);
//        copy.setAmount(amount);
//        copy.setState("出售中");
//        copy.setCreate_time(LocalDateTime.now());
//        copy.setUpdate_time(LocalDateTime.now());
//
//        //更新仓库
//        if(product.getPrice()==amount){
//            productService.delete(product.getId());
//        }else{
//            product.setAmount(product.getAmount()-amount);
//            productService.update(product);
//        }
//
//        //加入上架商品
//        productService.add(copy);
//
//        return Result.success(copy);
////    }
//
//    @PutMapping("unSale")
//    public Result unSale(@RequestParam Integer id){
//        Product product=productService.findById(id);
//    }