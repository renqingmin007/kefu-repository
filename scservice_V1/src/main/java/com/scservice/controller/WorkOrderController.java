package com.scservice.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.scservice.pojo.Admin;
import com.scservice.pojo.WorkOrder;
import com.scservice.service.AdminService;
import com.scservice.service.WorkOrderService;
import com.scservice.util.Exportutils;
import com.scservice.util.ResultModel;
import com.scservice.util.ResultTools;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("config")
public class WorkOrderController {

     @Autowired
     WorkOrderService workOrderService;
     @Autowired
     AdminService adminService;
     Integer sendRound = 0;
     Long agent_id;
    //工单录入并轮询派单接口
    @RequestMapping(value = "workOrderSubmit", method = RequestMethod.POST, produces = "application/json")
    public ResultModel WorkOrderSubmit(@RequestBody WorkOrder workOrder)
    {
         long role_id = 2;
        String name = workOrder.getWorkOrderName();
        String wechatid = workOrder.getWechatId();
        String lessee = workOrder.getWorkOrderLessee();
        String phone = workOrder.getWorkOrderPhone();
        String problem = workOrder.getWorkOrderProblem();
        if(name==null||name==""||wechatid==null||wechatid==""){
            return ResultTools.result(404,"基础信息缺失",null);
        }
        if(lessee==null||lessee==""||phone==null||phone==""||problem==null||problem==""){
            return ResultTools.result(404,"问题描述信息不全",null);
        }
        //******生成唯一工单编号
        Random random = new Random();
        // 随机数的量 自由定制，这是9位随机数
        Integer r = random.nextInt(900000000) + 100000000;
        // 返回  17位时间
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timeStr = sdf.format(new Date());
        // 17位时间+9位随机数
        workOrder.setWorkOrderUid(timeStr + r);
        //******添加工单状态
        workOrder.setWorkOrderStatus("未处理");
        //******添加工单开始时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String s = simpleDateFormat.format(date);
        workOrder.setWorkOrderStart(s);
        try
        {
            workOrderService.insertWorkOrder(workOrder);
            //轮询派单
            List<Admin> admin_server = adminService.selectAdmin_Server(role_id);
            Integer count = admin_server.size();
            if(sendRound <= count){
                agent_id = admin_server.get(sendRound).getId();
                sendRound++;
            }
            else {
                sendRound = 0;
                agent_id = admin_server.get(sendRound).getId();
                sendRound++;
            }
            workOrder.setAgentId(agent_id);
            workOrderService.sendWorkOrder(workOrder);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data",workOrder);
            return ResultTools.result(0, "录入并派单成功",map);
        }
        catch (Exception e)
        {
            return ResultTools.result(404, "录入派单失败",null);
        }
    }

    //删除工单接口
    @RequestMapping(value = "deleteOrder", method = RequestMethod.POST, produces = "application/json")
    public ResultModel DeleteOrder( @RequestParam Integer workOrderId)
    {
        try
        {
            workOrderService.deleteWorkOrder(workOrderId);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data",workOrderId);
            return ResultTools.result(0, "删除成功",map);
        }
        catch (Exception e)
        {
            return ResultTools.result(404, "删除失败",null);
        }
    }

    //修改工单接口
    @RequestMapping(value = "modifyOrder", method = RequestMethod.POST, produces = "application/json")
    public ResultModel ModifyOrder(@RequestBody WorkOrder workOrder)
    {
        try {

            workOrderService.modifyWorkOrder(workOrder);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", workOrder);
            return ResultTools.result(0, "修改成功",map);

        }catch (Exception e) {
            return ResultTools.result(404,"修改失败",null);
        }
    }

    //转派接口
    @RequestMapping(value = "deliverOrder", method = RequestMethod.POST, produces = "application/json")
        //转派到其他客服
    public ResultModel DeliverOrder(@RequestParam Long id,@RequestParam Integer workOrderId)
    {
        try{

            WorkOrder workOrder = workOrderService.searchOrderById(workOrderId);
            workOrder.setWorkOrderStatus("转派");
            workOrder.setAgentId(id);
            workOrderService.deliverWorkOrder(workOrder);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data",id);
            return ResultTools.result(0, "转派成功",map);

        }catch (Exception e) {
            return ResultTools.result(404,"转派失败",null);
        }
    }

    //查询工单接口（根据工单编号查询工单）
    @RequestMapping(value = "searchOrderByUid", method = RequestMethod.GET, produces = "application/json")
    public ResultModel searchOrderByUid(@RequestParam String uid)
    {
        try {
            List<WorkOrder> workerOrder = workOrderService.searchOrderByUid(uid);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data",workerOrder);
            return ResultTools.result(0, "查询成功",map);

        }catch (Exception e) {
            return ResultTools.result(404,"查询失败",null);
        }
    }

    //查询工单接口（根据工单联系电话查询工单）
    @RequestMapping(value = "searchOrderByPhone", method = RequestMethod.GET, produces = "application/json")
    public ResultModel SearchOrderByphone(@RequestParam String phone)
    {
        try {
            List<WorkOrder> workerOrder = workOrderService.searchOrderByPhone(phone);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data",workerOrder);
            return ResultTools.result(0, "查询成功",map);

        }catch (Exception e) {
            return ResultTools.result(404,"查询失败",null);
        }
    }


    //查询所有工单接口
    @RequestMapping(value = "searchAllOrder", method = RequestMethod.GET, produces = "application/json")
    public ResultModel SearchAllOrder(){
        try {
            List<WorkOrder> workOrder_all = workOrderService.searchAllOrder();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data",workOrder_all);
            return ResultTools.result(0, "查询成功",map);

        }catch (Exception e) {
            return ResultTools.result(404,"查询失败",null);
        }
    }

    //查询某个客服所有工单接口
    @RequestMapping(value = "searchByAgent", method = RequestMethod.GET, produces = "application/json")
    public ResultModel SearchByAgent(@RequestParam Long agentId){
        try {
            List<WorkOrder> work_agent = workOrderService.selectAgentId(agentId);
            Map<String,Object> map = new HashMap<>();
            map.put("data",work_agent);
            return ResultTools.result(0, "查询成功",map);
        }catch (Exception e) {
            return ResultTools.result(404,"查询失败",null);
        }
    }

    //查询所有待办工单接口
    @RequestMapping(value = "searchTodoList", method = RequestMethod.GET, produces = "application/json")
    public ResultModel SearchTodoList(){
        try {

            List<WorkOrder> todoList = workOrderService.selectTodoList();
            Map<String,Object> map = new HashMap<>();
            map.put("data",todoList);
            return ResultTools.result(0, "查询成功",map);
        }catch (Exception e) {
            return ResultTools.result(404,"查询失败",null);
        }
    }

    //查询所有已处理工单接口
    @RequestMapping(value = "searchDoList", method = RequestMethod.GET, produces = "application/json")
    public ResultModel SearchDoList(){
        try {
            List<WorkOrder> doList = workOrderService.selectDoList();
            Map<String,Object> map = new HashMap<>();
            map.put("data",doList);
            return ResultTools.result(0, "查询成功",map);
        }catch (Exception e) {
            return ResultTools.result(404,"查询失败",null);
        }
    }
    //导出Excel接口
    @RequestMapping(value = "exportWorkOrder", method = RequestMethod.POST, produces = "application/json")
    public ResultModel ExportWorkOrder(List<Long> id_list, HttpServletResponse response, HttpSession session) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式  HH:mm:ss
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        ClassPathResource classPathResource = new ClassPathResource("application.properties");
        String filepath = classPathResource.getPath()+"/excel/";
        File filep = new File(filepath);
        if(!filep.exists())
        {
            filep.mkdirs();
        }

        List<WorkOrder> wo_list = new ArrayList<>();
        for(int i = 0;i<id_list.size();i++)
        {
            WorkOrder temp = workOrderService.searchOrderById(i);
            if(temp!=null)
            {
                wo_list.add(temp);
            }

        }

        TemplateExportParams params = new TemplateExportParams();
        // 标题开始行
        params.setHeadingStartRow(0);
        // 标题行数
        params.setHeadingRows(1);
        // 设置sheetName，若不设置该参数，则使用得原本得sheet名称
        params.setSheetName("工单列表");

        // 获取报表内容
        // 因为表数据是根据存储过程来实现的，不同的报表有不同的配置，
        // 所以使用Map<String,Object>格式来接收
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", wo_list);
        // 获取模板文件路径
        // 这里有个很坑的地方，就是easypoi的API只能接收文件路径，无法读取文件
        // 设置模板路径
        params.setTemplateUrl(filepath);
        // 获取workbook
        Workbook workbook = ExcelExportUtil.exportExcel(params, data);
        // exportFileName代表导出的文件名称
        Exportutils.export(response, workbook, date+".xls");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("exportmsg","success");
        return ResultTools.result(0, "",map);
    }

    //图片上传接口
    @RequestMapping(value = "imageUpload", method = RequestMethod.POST, produces = "application/json")
    public ResultModel ImageUpload(MultipartFile file, Long workorder_id) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式  HH:mm:ss
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        //获取项目resource文件夹路径
        ClassPathResource classPathResource = new ClassPathResource("application.properties");
        String path = classPathResource.getPath()+"/image/";
        String sourcename = file.getOriginalFilename();
        String extendName = sourcename.substring(sourcename.lastIndexOf("."), sourcename.length());
        String fileName =  workorder_id + date+ extendName;

        //存储图片
        File dir = new File(path, fileName);
        File filepath = new File(path);
        if(!filepath.exists())
        {
            filepath.mkdirs();
        }
        file.transferTo(dir);

        //将图片地址存入数据库
        workOrderService.imageStorage(workorder_id, path+fileName);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("insertmsg",fileName+"已保存");
        return ResultTools.result(0, "",map);
    }

}
