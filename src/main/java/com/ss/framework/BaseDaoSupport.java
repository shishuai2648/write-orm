package com.ss.framework;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class BaseDaoSupport<T extends StringBuilder,PK extends Serializable>{

    protected abstract void setDataSource(DataSource dataSource);

    public List<T> select(QueryRule queryRule){
        QueryRuleSqlBuilder builder = new QueryRuleSqlBuilder(queryRule);
        String whereSql = builder.getWhereSql();
        builder.getOrderSql();
        builder.getValues();
        return null;
    }

    // SpringJDBC，不改变原有方法，只设定了一种模式
    public List<Map<String,Object>> selectBySql(String sql, Object... args){
        return null;
    }

}
