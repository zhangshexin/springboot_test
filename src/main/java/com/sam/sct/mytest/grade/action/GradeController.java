package com.sam.sct.mytest.grade.action;

import com.alibaba.fastjson.JSONObject;
import com.sam.sct.mytest.entity.Grade;
import com.sam.sct.mytest.entity.User;
import com.sam.sct.mytest.grade.service.GradeService;
import com.sam.sct.mytest.grade.vo.GradeVO;
import com.sam.sct.mytest.ucenter.service.UserService;
import com.sam.sct.mytest.ucenter.vo.GradeWithRank;
import com.sam.sct.mytest.util.ResultUtile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangshexin
 * @createTime 2019/4/17
 */
@Api(tags = "分数及排名接口")
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private UserService userService;
    /**
     * 返回前多少名的成绩
     * @param top
     * @return
     */
    @ApiOperation(value="返回前多少名的成绩", notes="返回前多少名的成绩",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "返回前多少名",type ="Integer" ),
            @ApiImplicitParam(name = "specialId",value = "专题id",type ="Integer")
    })
    @RequestMapping(value = "/top/{num}/{specialId}",method = RequestMethod.GET)
    public Object getGradeTop(@PathVariable(value = "num")int top ,@PathVariable("specialId")int specialId){
        return ResultUtile.result(ResultUtile.SUCCESS,null,gradeService.selectTop(top,specialId));
    }

    /**
     * 获取用户的成绩，及排名
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户的成绩，及排名",notes = "获取用户的成绩，及排名",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", type = "Integer"),
            @ApiImplicitParam(name = "specialId", value = "专题id", type = "Integer"),
            @ApiImplicitParam(name = "top", value = "排名前多少", type = "Integer")
    })
    @RequestMapping(value = "/{userId}/{specialId}/{top}",method = RequestMethod.GET)
    public Object getUserGrade(@PathVariable("userId")int userId,@PathVariable("specialId")int specialId,@PathVariable("top")int top){
        GradeWithRank gradeWithRank=gradeService.seleGradeByUserId(userId,specialId);
        GradeVO gradeVO= JSONObject.parseObject(JSONObject.toJSONString(gradeWithRank),GradeVO.class);
        List<Grade> topList=gradeService.selectTop(top,specialId);
        String userids="";
        for (Grade g :
                topList) {
            userids=userids+g.getUserId()+",";
        }
        List<User> users=userService.findByIds(userids);
        //把用户手机号放入grade中
        if(users!=null&&users.size()>0)
        for (Grade g:
             topList) {
            for (int i = 0; i < users.size(); i++) {
                if(g.getUserId()==users.get(i).getId())
                {
                    g.setPhoneNum(users.get(i).getPhoneNumber());
                    i=users.size()+1;
                }
            }
        }
        gradeVO.setTopN(topList);
        return ResultUtile.result(ResultUtile.SUCCESS,null,gradeVO);
    }

    /**
     * 保存或更新成绩
     * @param userId
     * @param grade
     * @return
     */
    @ApiOperation(value = "保存或更新成绩",notes = "保存或更新成绩",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", type = "Integer"),
            @ApiImplicitParam(name = "grade", value = "成绩", type = "Integer")
    })
    @RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
    public Object saveOrUpdateGrade(@RequestParam("userId")int userId,@RequestParam("grade")int grade){
        Grade grade1=new Grade();
        grade1.setUserId(userId);
        grade1.setGrade(grade);
        return ResultUtile.result(ResultUtile.SUCCESS,null,gradeService.saveOrUpdateGrade(grade1));
    }
}
