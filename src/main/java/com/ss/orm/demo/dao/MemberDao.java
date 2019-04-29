package com.ss.orm.demo.dao;

import com.ss.framework.BaseDaoSupport;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Component
public class MemberDao extends BaseDaoSupport {

    @Override
    protected String getPKColumn() {
        return null;
    }

    @Override
    @Resource
    protected void setDataSource(DataSource dataSource) {
        System.out.println(dataSource);
    }
}
