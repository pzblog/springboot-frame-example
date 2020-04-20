package com.example.boot.test.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.boot.core.converter.LocalDateSerializer;
import com.example.boot.core.converter.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author panzhi
 * @since 2019-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_test")
@NoArgsConstructor
public class TestEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;
    
    /**
     * 修改日期
     */
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate updateTime;

	public TestEntity(String id, String name, LocalDateTime createTime, LocalDate updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

}
