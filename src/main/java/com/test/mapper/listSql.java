package com.test.mapper;

import java.util.Map;

public class listSql {
    public String search(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer("SELECT * from comments");

        Integer user_id = (Integer) map.get("user_id");
        Integer store_id = (Integer) map.get("store_id");

        if (user_id != null || store_id != null) {
            sb.append(" where");
        }

        boolean tag = false;
        if (store_id != null && store_id.intValue() > 0) {
            if (tag) sb.append(" and");
            sb.append(" store_id=#{store_id}");
            tag = true;
        }
        if (user_id != null && user_id.intValue() > 0) {
            if (tag) sb.append(" and");
            sb.append(" user_id=#{user_id}");
            tag = true;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
