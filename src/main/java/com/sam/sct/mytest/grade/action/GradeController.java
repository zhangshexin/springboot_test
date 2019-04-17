package com.sam.sct.mytest.grade.action;

import com.sam.sct.mytest.entity.Grade;
import com.sam.sct.mytest.grade.service.GradeService;
import com.sam.sct.mytest.util.ResultUtile;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangshexin
 * @createTime 2019/4/17
 */
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;
    /**
     * 返回前多少名的成绩
     * @param top
     * @return
     */
    @ApiOperation(value="返回前多少名的成绩", notes="返回前多少名的成绩",produces = "application/json")
    @RequestMapping("/top/{num}")
    public Object getGradeTop(@PathVariable(value = "num")int top ){
        return ResultUtile.result(ResultUtile.SUCCESS,null,gradeService.selectTop(top));
    }

    /**
     * 获取用户的成绩，及排名
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户的成绩，及排名",notes = "获取用户的成绩，及排名",produces = "application/json")
    @RequestMapping("/{userId}")
    public Object getUserGrade(@PathVariable("userId")int userId){
        return ResultUtile.result(ResultUtile.SUCCESS,null,gradeService.seleGradeByUserId(userId));
    }

    /**
     * 保存或更新成绩
     * @param userId
     * @param grade
     * @return
     */
    @ApiOperation(value = "保存或更新成绩",notes = "保存或更新成绩",produces = "application/json")
    @RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
    public Object saveOrUpdateGrade(@RequestParam("userId")int userId,@RequestParam("grade")int grade){
        Grade grade1=new Grade();
        grade1.setUserId(userId);
        grade1.setGrade(grade);
        return ResultUtile.result(ResultUtile.SUCCESS,null,gradeService.saveOrUpdateGrade(grade1));
    }
}
