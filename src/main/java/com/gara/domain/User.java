package com.gara.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @description:
 * @author: GaraYing
 * @create: 2018-10-30 10:14
 **/
@Data  // 生成无参构造方法/getter/setter/hashCode/equals/toString
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {

    @Id
    private String id; //注解属性为ID
    @Indexed(unique = true)
    private String username; // 注解属性username为索引，并且不可以重复
    private String phone;
    private String email;
    private String name;
    private Date birthday;
}
