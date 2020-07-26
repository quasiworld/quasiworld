package com.qwplus.quasiworld.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//id

    private String userName;//用户名称

    private String passWord;//密码

    private String phone;//电话

    private Date createDate;//时间

    private Boolean status;//状态


}
