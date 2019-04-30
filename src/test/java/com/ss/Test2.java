package com.ss;

import com.ss.orm.demo.dao.Member;
import com.ss.orm.demo.dao.MemberDao;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test2 {

    @Autowired
    private MemberDao memberDao;


    @Test
    @Ignore
    public void testSelect(){
        try {
            List<Member> tom = memberDao.selectAll();
            System.out.println(tom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() throws Exception {
        for( int age = 35 ; age > 25 ; age -- ){
            Member member = new Member();
            member.setName("Jams" + age);
            member.setAddr("beijing" + age);
            member.setAge(age);
            memberDao.insert(member);
            System.out.println(member.getId());
        }
    }
}
