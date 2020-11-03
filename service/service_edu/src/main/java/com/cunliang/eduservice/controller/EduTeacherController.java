package com.cunliang.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cunliang.commonutils.R;
import com.cunliang.eduservice.entity.EduTeacher;
import com.cunliang.eduservice.entity.vo.TeacherQuery;
import com.cunliang.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    //分页查询方法
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        //创建page对象

        Page<EduTeacher> teacherPage = new Page<>(current,limit);

        teacherService.page(teacherPage,null);
        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();
        return R.OK().data("total",total).data("rows",records);
    }


    //条件查询带分页

    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                    @RequestBody(required = false) TeacherQuery teacherQuery){
        //创建配置对象
        Page <EduTeacher> pageTeacher = new Page<>(current,limit);
        QueryWrapper <EduTeacher> queryWrapper = new QueryWrapper<>();
        //多条件查询 动态SQL
        //判断条件值
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_modified",end);
        }

        teacherService.page( pageTeacher,queryWrapper);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.OK().data("total",total).data("rows",records);

    }

    //添加讲师
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if (save){
            return R.OK();
        }else {
            return R.ERROR();
        }
    }


    //根据讲师ID查询
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.OK().data("teacher",eduTeacher);
    }

    @PostMapping("updateTeacher")

    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = teacherService.updateById(eduTeacher);
        if (b){
            return R.OK();
        }else {
            return R.ERROR();
        }
    }
}
