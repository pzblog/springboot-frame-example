package com.example.demo.service;

import com.example.demo.model.entity.Dept;
import com.example.demo.model.vo.DeptVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DeptService {

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Dept> findPageList(int pageNum, int pageSize);

    /**
     * 查询所有数据
     * @return
     */
    List<DeptVo> findAllList();

    /**
     * 通过部门ID查询部门信息
     * @param deptId
     * @return
     */
    Dept findDeptInfo(Long deptId);

    /**
     * 新增数据
     * @param dept
     */
    void insertInfo(Dept dept);

    /**
     * 修改数据
     * @param dept
     */
    void updateInfo(Dept dept) throws Exception;

    /**
     * 删除数据
     */
    void deleteAll();


    void saveOrUpdate(Dept dept);

    Integer testSingle();
}
