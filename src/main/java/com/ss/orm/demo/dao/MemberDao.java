package com.ss.orm.demo.dao;

import com.ss.framework.BaseDaoSupport;
import com.ss.framework.QueryRule;
import com.ss.orm.demo.config.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@Component
public class MemberDao extends BaseDaoSupport<Member,Long> {

    @Override
    protected String getPKColumn() {
        return "id";
    }


    private DynamicDataSource dataSource;

    @Override
    @Resource
    protected void setDataSource(DataSource dataSource) {
        this.dataSource = (DynamicDataSource )dataSource;
        this.setDataSourceReadOnly(dataSource);
        this.setDataSourceWrite(dataSource);
//        this.setDataSource(dataSource);
    }

    public List<Member> selectByName(String name) throws Exception{
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.andEqual("name",name);

        return super.select(queryRule);
    }

    public List<Member> selectAll() throws Exception{
        QueryRule queryRule =  QueryRule.getInstance();
        return super.select(queryRule);
    }


    public boolean insert(Member member) throws Exception {

        if( member.getAge() >= 30 ){
            this.dataSource.getDataSourceEntry().set("write");
        }

        Long id = super.insertAndReturnId(member);
        member.setId(id);

        this.dataSource.getDataSourceEntry().set("read");

        return id > 0;
    }
}
