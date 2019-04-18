package com.sam.sct.mytest.grade.action;

import com.sam.sct.mytest.entity.Special;
import com.sam.sct.mytest.grade.service.SpecialService;
import com.sam.sct.mytest.util.ResultUtile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangshexin
 * @createTime 2019/4/18
 */
@Api(tags = "专题接口")
@RestController
@RequestMapping("/special")
public class SpecialController {

    @Autowired
    private SpecialService specialService;

    /**
     * 返回所有可用的专题
     * @return
     */
    @ApiOperation(value="返回所有可用的考题", notes="返回所有可用的考题",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页码",type ="Integer" ),
            @ApiImplicitParam(name = "pageSize",value = "一页多少条",type ="Integer"),
            @ApiImplicitParam(name = "status",value = "1：未删除，0：已删除",type ="Integer")
    })
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Object getSpecialList(@RequestParam(value = "status")int status, @RequestParam(value = "pageNum")int pageNum, @RequestParam(value = "pageSize")int pageSize){
        return ResultUtile.result(ResultUtile.SUCCESS,null,specialService.getPageSpecial(pageNum,pageSize,status));
    }

    /**
     * 保存或更新专题
     * @param special
     * @return
     */
    @ApiOperation(value="保存或更新专题", notes="保存或更新专题",produces = "application/json")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Object saveOrUpdateSpecial(@RequestBody Special special){
        if(special.getId()==0){
            return ResultUtile.result(ResultUtile.SUCCESS,null, specialService.saveSpecial(special));
        }else{
            return ResultUtile.result(ResultUtile.SUCCESS,null, specialService.upDateSpecial(special));
        }
    }

    /**
     * 根据专题的ids批量删除
     * @param ids
     * @return
     */
    @ApiOperation(value="根据专题的ids批量删除", notes="ids必须以项文逗号分隔",produces = "application/json")
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
        return ResultUtile.result(ResultUtile.SUCCESS,null,specialService.deleteSpecial(_ids));
    }

    /**
     * 根据专题的ids批量恢复
     * @param ids
     * @return
     */
    @ApiOperation(value="根据专题的ids批量恢复", notes="ids必须以项文逗号分隔",produces = "application/json")
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
        return ResultUtile.result(ResultUtile.SUCCESS,null,specialService.recoverSpecial(_ids));
    }

}
