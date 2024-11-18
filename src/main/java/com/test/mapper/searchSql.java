package com.test.mapper;

import java.util.Map;

public class searchSql {
    public String search(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer("SELECT * from products");
        String state = (String) map.get("state");
        String category = (String) map.get("category");
        Integer min_price = (Integer) map.get("min_price");
        Integer max_price = (Integer) map.get("max_price");
        Integer store_id = (Integer) map.get("store_id");
        Integer consumer_id = (Integer) map.get("consumer_id");
        String description = (String) map.get("description");
        String name = (String) map.get("name");

        if (state!=null || category!=null || min_price!=null || max_price!=null || store_id!=null
                || description!=null || name!=null) {
            sb.append(" where");
        }

        boolean tag=false;

        if (state != null && state.length()>0) {
            if(tag)sb.append(" and");
            sb.append(" state=#{state}");
            tag=true;
        }
        if (category != null && category.length()>0) {
            if(tag)sb.append(" and");
            sb.append(" category=#{category}");
            tag=true;
        }
        if (min_price != null && min_price.intValue()>0) {
            if(tag)sb.append(" and");
            sb.append(" price>=#{min_price}");
            tag=true;
        }
        if (max_price != null && max_price.intValue()>0) {
            if(tag)sb.append(" and");
            sb.append(" price<=#{max_price}");
            tag=true;
        }
        if (store_id != null && store_id.intValue()>0) {
            if(tag)sb.append(" and");
            sb.append(" store_id=#{store_id}");
            tag=true;
        }
        if (consumer_id != null && consumer_id.intValue()>0) {
            if(tag)sb.append(" and");
            sb.append(" consumer_id=#{consumer_id}");
            tag=true;
        }
        if (description != null && description.length()>0) {
            if(tag)sb.append(" and");
            sb.append(" description like concat('%',#{description},'%')");
            tag=true;
        }
        if (name != null && name.length()>0) {
            if(tag)sb.append(" and");
            sb.append(" product_name like concat('%',#{name},'%')");
            tag=true;
        }
        System.out.println(sb.toString());
        return sb.toString();


    }
}
