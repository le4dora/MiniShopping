package com.test.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.mapper.CommentMapper;
import com.test.pojo.Comment;
import com.test.pojo.PageBen;
import com.test.service.CommentService;
import com.test.util.ThreadLocalUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void add(Comment comment) {
        //获取id
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer user_id=(Integer) map.get("id");
        comment.setUser_id(user_id);
        comment.setUser_id(user_id);
        commentMapper.add(comment);
    }

    @Override
    public PageBen<Comment> list(Integer pageNum, Integer pageSize,Integer user_id, Integer store_id) {


        PageHelper.startPage(pageNum,pageSize);

        Page<Comment> p=(Page<Comment>)commentMapper.list(user_id,store_id);
        return new PageBen<>(p.getTotal(),p);
//        return new PageBen(p.getTotal(),commentMapper.list(user_id,store_id));
    }

    @Override
    public void delete(Integer id) {
        commentMapper.delete(id);
    }
}
