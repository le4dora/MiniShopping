package com.test.controller;

import com.test.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.test.pojo.Comment;
import com.test.pojo.PageBen;
import com.test.pojo.Result;
import com.test.service.CommentService;

import java.util.Map;

import static java.time.LocalTime.now;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;



    @PutMapping
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
        return Result.success("当前时间： "+now()+" 来自于 "+"MiniShopping ");
    }

    @GetMapping
    public Result<PageBen> Search(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer user_id,
            @RequestParam(required = false) Integer store_id
    ) {
        //获取id
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id=(Integer) map.get("id");
        if(user_id==null && store_id==null && id!=0) {
            return Result.error("缺失必要参数");
        }
        PageBen<Comment> pageBen=commentService.list(pageNum, pageSize, user_id,store_id);
        return Result.success(pageBen);
    }

    @DeleteMapping
    public Result delete(@RequestParam  Integer id) {//删除评论
        commentService.delete(id);
        return Result.success("当前时间： "+now()+" 来自于 "+"MiniShopping ");
    }
}
