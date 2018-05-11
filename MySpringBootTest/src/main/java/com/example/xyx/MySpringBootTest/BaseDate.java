package com.example.xyx.MySpringBootTest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="basedate")
public class BaseDate {

    private String name;
    private String age;
    private String sex;
    private String love;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getLove() {
        return love;
    }
    public void setLove(String love) {
        this.love = love;
    }
    
}
