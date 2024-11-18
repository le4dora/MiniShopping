package com.test.service.impl;

import com.test.mapper.AdministratorMapper;
import com.test.pojo.Administrator;
import com.test.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorSerciveImpl implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public Administrator findByName(String name) {
        return administratorMapper.findByName(name);
    }
}
