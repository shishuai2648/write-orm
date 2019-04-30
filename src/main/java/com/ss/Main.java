package com.ss;

import com.ss.orm.demo.dao.Member;
import com.ss.orm.demo.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.List;
import java.util.Map;

@SpringBootApplication( exclude = DataSourceAutoConfiguration.class )
public class Main {

    @Autowired
    private MemberDao memberDao;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class,args);
    }
}
