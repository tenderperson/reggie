package com.zyf.reggie.Controller;
import com.github.pagehelper.PageInfo;
import com.zyf.reggie.Service.EmployeeService;
import com.zyf.reggie.common.R;
import com.zyf.reggie.pojo.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        Employee emp = employeeService.employeeByName(employee.getUsername());
        System.out.println(emp);
        if (emp==null){
            return R.error("登录失败");
        }
        if (!emp.getPassword().equals(password)){
            return R.error("登录失败");
        }
        if (emp.getStatus()==0){
            return R.error("该账号被禁用");
        }
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);

    }
    @RequestMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
    @PostMapping
    public R<String> add(HttpServletRequest request,@RequestBody Employee employee){
        Random r =new Random();
        Date date =new Date();
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setStatus(1);
        employee.setCreateTime(date);
        employee.setUpdateTime(date);
        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        employee.setId(r.nextLong());
        employeeService.insertEmployee(employee);
        return R.success("新增员工成功");
    }
    @GetMapping("/page")
    public R<PageInfo>employee(int page, int pageSize,String name){
        if (name==null){
            PageInfo<Employee> allEmployee = employeeService.getAllEmployee(page, pageSize);
            return R.success(allEmployee) ;
        }
        Employee employee = employeeService.employeeByName(name);
        List<Employee> list =new ArrayList<>();
        list.add(employee);
        PageInfo<Employee> employeePageInfo =new PageInfo<>(list);
        return R.success(employeePageInfo);

    }
    @GetMapping("/{id}")
    public R<Employee> update(@PathVariable Integer id){
        Employee employeeById = employeeService.getEmployeeById(id);
        return R.success(employeeById);
    }
    @PutMapping
    public R<String> stop(HttpServletRequest request,@RequestBody Employee employee){
        log.info(employee.toString());
        Date date =new Date();
        employee.setUpdateTime(date);
        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setUpdateUser(empId);
        if (employee.getUsername()==null){
            employeeService.update(employee);
            return R.success("禁用员工成功");
        }
        employeeService.updateEmployeeById(employee);
        return R.success("修改成功");
    }
}
