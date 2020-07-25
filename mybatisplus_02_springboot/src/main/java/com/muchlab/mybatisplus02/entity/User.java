package com.muchlab.mybatisplus02.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "t_user")//与数据库中的t_user建立关系
public class User implements Serializable {
    //若属性名和字段名一致，则可以不加value
    //代表主键
    @TableId(value = "id")
    private Integer id;
    @TableField(value = "name")
    private String name;
    private String address;
    private Date birth;

    public User(String name, String address, Date birth) {
        this.name = name;
        this.address = address;
        this.birth = birth;
    }
}

