package com.test.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import com.test.pojo.Comment;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    @Insert("insert into comments(user_id,store_id,commentary,create_time,update_time)"+
            " values (#{user_id},#{store_id},#{commentary},now(),now())")
    void add(Comment comment);

    @Delete("delete from comments where id=#{id}")
    void delete(Integer id);

    @SelectProvider(type = listSql.class,method = "search")
    List<Comment> list(Integer user_id, Integer store_id);
}
