package com.example.demo.web;

import com.example.demo.core.execption.CommonExecption;
import com.example.demo.model.entity.Dept;
import com.example.demo.model.vo.DeptVo;
import com.example.demo.service.DeptService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
//@Scope("request")
public class DeptApiController {

    private static final Logger log = LoggerFactory.getLogger(DeptApiController.class);

    @Autowired
    private DeptService deptService;

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("selectPage")
    public PageInfo<Dept> selectPage(@RequestParam(value = "pageNum",required = false) Integer pageNum,
                                     @RequestParam(value = "pageSize",required = false) Integer pageSize){
        pageNum = (pageNum == null ? 1 : pageNum);
        pageSize = (pageSize == null ? 10 : pageSize);
        return deptService.findPageList(pageNum, pageSize);
    }

    /**
     * 查询所有信息
     * @return
     */
    @RequestMapping("queryAll")
    public List<DeptVo> queryAll(){
        return deptService.findAllList();
    }


    @RequestMapping("hello1")
    public void hello1(){
        throw new CommonExecption("自定义异常");
    }

    @RequestMapping("hello2")
    public void hello2() throws Exception {
        System.out.println("hello2......");
        throw new Exception();
    }

    @RequestMapping("hello3")
    public void hello3(){
        System.out.println("hello3......");
    }


    @RequestMapping("hello4")
    public Integer hello4(){
        int num =0;
        return num ++;
    }

    @RequestMapping("hello5")
    public Integer hello5(){
        return deptService.testSingle();
    }
}
