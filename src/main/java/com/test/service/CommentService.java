package com.test.service;

import com.test.pojo.Comment;
import com.test.pojo.PageBen;

public interface CommentService {

    //添加评论
    void add(Comment comment);

    //查询评论
    PageBen list(Integer pageNum, Integer pageSize,Integer userId, Integer storeId);

    //删除评论
    void delete(Integer id);
}
