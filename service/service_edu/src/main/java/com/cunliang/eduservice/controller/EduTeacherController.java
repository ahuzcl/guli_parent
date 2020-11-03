package com.cunliang.eduservice.controller;


import com.cunliang.commonutils.R;
import com.cunliang.eduservice.entity.EduTeacher;
import com.cunliang.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-03
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")

public class EduTeacherController {


    /**
     * 查询数据
     * controller要调用service中的方法，因此先注入service
     * 同理，service中注入mapper
     */

    //把service注入
    @Autowired
    private EduTeacherService teacherService;
    //查询所有讲师的数据
    //rest风格

    @GetMapping("findAll")
    @ApiOperation(value = "所有讲师列表")
    public R   findAllTeacher(){
        //调用service方法查询所有讲师信息
        List<EduTeacher> list = teacherService.list(null);
        return R.OK().data("items",list);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}") //通过路径删除，获取路径中的输入ID。
    public R removeTeacher(
            @ApiParam(name = "id",value = "讲师ID",required = true)
            @PathVariable String id){
        boolean b = teacherService.removeById(id);
        if (b){
            return R.OK();
        }else{
            return R.ERROR();
        }

    }

}

