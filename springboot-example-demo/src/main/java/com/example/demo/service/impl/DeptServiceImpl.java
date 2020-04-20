package com.example.demo.service.impl;

import com.example.demo.dao.DeptMapper;
import com.example.demo.model.entity.Dept;
import com.example.demo.model.vo.DeptVo;
import com.example.demo.service.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    private int num = 0;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private DataSourceTransactionManager transactionManager;


    @Override
    public PageInfo<Dept> findPageList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Dept> deptList = deptMapper.findList();
//        List<DeptVo> dataList = warpDeptInfo(deptList);
        return new PageInfo<>(deptList);
    }

    @Override
    public List<DeptVo> findAllList() {
        List<Dept> deptList = deptMapper.findList();
        return warpDeptInfo(deptList);
    }



    @Override
    public Dept findDeptInfo(Long deptId) {
        return deptMapper.findDeptInfo(deptId);
    }

    @Transactional
    @Override
    public void insertInfo(Dept dept) {
        deptMapper.insertInfo(dept);
    }

    @Transactional
    @Override
    public void updateInfo(Dept dept) throws Exception {
        deptMapper.deleteAll();
        if(dept != null){
            throw new NullPointerException();
        }
        deptMapper.updateInfo(dept);
    }

    @Transactional
    @Override
    public void deleteAll() {
        deptMapper.deleteAll();
    }

    @Override
    public void saveOrUpdate(Dept dept) {
        //1、获取事务定义
        DefaultTransactionDefinition definitione = new DefaultTransactionDefinition();
        //2、设置事务隔离级别，开启新事物
        definitione.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        //3、获取事务状态
        TransactionStatus status = transactionManager.getTransaction(definitione);

        try {

            Dept queryObj = deptMapper.findDeptInfo(dept.getDeptId());
            if(queryObj != null){
                deptMapper.updateInfo(dept);
            }else{
                deptMapper.deleteAll();
                deptMapper.insertInfo(dept);
            }
            //手动提交事务
            transactionManager.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            //手动事务回滚
            transactionManager.rollback(status);
        }
    }

    @Override
    public Integer testSingle() {
        return num++;
    }


    private List<DeptVo> warpDeptInfo(List<Dept> deptList){
        List<DeptVo> dataList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(deptList)){
            for (Dept dept : deptList) {
                dataList.add(new DeptVo(dept));
            }
        }
        return dataList;
    }
}
