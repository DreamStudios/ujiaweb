package com.blueshit.joke.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 笑点分类实体
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/25 15:59
 * @description
 */
@Entity
@Table(name = "type_info")
@NamedQuery(name = "TypeInfo.findAll", query = "SELECT t FROM TypeInfo t")
public class TypeInfo implements Serializable {

    private static final long serialVersionUID = -3283104129450531566L;
    /**
     *  类型ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Integer id;

    /**
     *  类型名称
     */
    @Column(nullable = false)
    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
