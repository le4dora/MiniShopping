package com.test.mapper;

import com.test.pojo.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdministratorMapper {

    @Select("select * from administrators where name=#{name}")
    Administrator findByName(String name);


}
