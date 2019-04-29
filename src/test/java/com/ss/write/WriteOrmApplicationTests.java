package com.ss.write;

import com.ss.orm.demo.dao.MemberDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WriteOrmApplicationTests {

     @Autowired
     private MemberDao memberDao;

    @Test
    public void contextLoads() {
        System.out.println(memberDao);
    }

}
