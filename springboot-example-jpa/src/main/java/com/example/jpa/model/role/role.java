package com.example.jpa.model.role;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class role implements Serializable {

    private static final long serialVersionUID = 2850928388503147321L;

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue
    private Long id;


    /* 角色名称
     */
    @Column(nullable = false)
    private String roleName;

    /**
     * 角色类型
     */
    @Column(nullable = false)
    private String roleType;

    /**
     * 最后修改时间
     */
    @Column(nullable = false)
    @Transient
    private String lastTime;
}
