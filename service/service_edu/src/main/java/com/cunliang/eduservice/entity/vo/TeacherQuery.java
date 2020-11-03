package com.cunliang.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeacherQuery {

    @ApiModelProperty(value = "教师姓名，模糊查询")
    private String name;
    @ApiModelProperty(value = "级别 1高级讲师 2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间",example = "2020-11-03 19:00:00")
    private String begin;
    @ApiModelProperty(value = "查询结束时间",example = "2020-11-04 18:59:59")
    private String end;




}
