package com.sam.sct.mytest.grade.action;

import com.sam.sct.mytest.entity.Questions;
import com.sam.sct.mytest.grade.service.QuestionsService;
import com.sam.sct.mytest.util.ResultUtile;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangshexin
 * @createTime 2019/4/18
 */
@Api(tags = "考题接口")
@RestController
@RequestMapping(value = "/questions")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;


    /**
     * 返回所有可用的考题
     * @return
     */
    @ApiOperation(value="返回所有可用的考题", notes="返回所有可用的考题",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页码",type ="Integer" ),
            @ApiImplicitParam(name = "pageSize",value = "一页多少条",type ="Integer"),
            @ApiImplicitParam(name = "specialId",value = "专题id",type ="Integer"),
            @ApiImplicitParam(name = "status",value = "1：未删除，0：已删除",type ="Integer")
    })
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Object getActivityQuestionsList(@RequestParam(value = "specialId")int specialId,@RequestParam(value = "status")int status,@RequestParam(value = "pageNum")int pageNum,@RequestParam(value = "pageSize")int pageSize){
        return ResultUtile.result(ResultUtile.SUCCESS,null,questionsService.getPageQuestions(pageNum,pageSize,specialId,status));
    }

    /**
     * 根据考题的ids返回对应的考题列表
     * @param ids
     * @return
     */
    @ApiOperation(value="根据考题的ids返回对应的考题列表", notes="ids必须以项文逗号分隔",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value = "id数组",type ="String" )
    })
    @RequestMapping(value = "/getIdsList",method = RequestMethod.GET)
    public Object getQuestionsByIds(@RequestParam(value = "ids",required = true)String ids){
        String[] tmp = ids.split(",");
        int[] _ids = new int[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            _ids[i] = Integer.valueOf(tmp[i]);
        }
        return ResultUtile.result(ResultUtile.SUCCESS,null,questionsService.selectBySpecialId(_ids));
    }

    /**
     * 保存或更新考题
     * @param question
     * @return
     */
    @ApiOperation(value="保存或更新考题", notes="保存或更新考题",produces = "application/json")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Object saveOrUpdateQuestion(@RequestBody Questions question){
        //根据id判断是更新还是插入
        if(question.getId()==0){
            return ResultUtile.result(ResultUtile.SUCCESS,null, questionsService.saveQuestion(question));
        }else{
            return ResultUtile.result(ResultUtile.SUCCESS,null, questionsService.updateQuestion(question));
        }
    }

    /**
     * 根据考题的ids批量删除
     * @param ids
     * @return
     */
    @ApiOperation(value="根据考题的ids批量删除", notes="ids必须以项文逗号分隔",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value = "id数组",type ="String" )
    })
    @RequestMapping(value = "/deleteByIds",method = RequestMethod.POST)
    public Object deleteByIds(@RequestParam(value = "ids") String ids){
        String[] tmp = ids.split(",");
        int[] _ids = new int[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            _ids[i] = Integer.valueOf(tmp[i]);
        }
        return ResultUtile.result(ResultUtile.SUCCESS,null,questionsService.deleteQuestion(_ids));
    }

    /**
     * 根据考题的ids批量恢复
     * @param ids
     * @return
     */
    @ApiOperation(value="根据考题的ids批量恢复", notes="ids必须以项文逗号分隔",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value = "id数组",type ="String" )
    })
    @RequestMapping(value = "/recoverByIds",method = RequestMethod.POST)
    public Object recoverByIds(@RequestParam(value = "ids") String ids){
        String[] tmp = ids.split(",");
        int[] _ids = new int[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            _ids[i] = Integer.valueOf(tmp[i]);
        }
        return ResultUtile.result(ResultUtile.SUCCESS,null,questionsService.recoverQuestion(_ids));
    }
}
