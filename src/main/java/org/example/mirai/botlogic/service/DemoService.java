package org.example.mirai.botlogic.service;

import org.example.mirai.botlogic.db.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hundun
 * Created on 2021/07/01
 */
@Service
public class DemoService {
    @Autowired
    DemoRepository repository;
    
    public String checkIoc() {
        return "my repository " + (repository == null ? "is " : "is not ") + "null";
    }

    public String work() {
        return "Hello world, repository has " + repository.count() + " document(s).";
    }
}
