package com.ss.write;

import com.ss.framework.QueryRule;

public class Test1 {

    public static void main(String[] args) {
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.andBetween("name","tom"); // and name = 'tom'

    }
}
