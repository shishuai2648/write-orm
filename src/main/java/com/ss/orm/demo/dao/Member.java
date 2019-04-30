package com.ss.orm.demo.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "t_member" )
@Data
public class Member implements Serializable {

    @Id
    Long id;

    String name;

    String addr;

    Integer age;



}
