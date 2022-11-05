package com.zyf.reggie.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyf.reggie.Mappers.EmployeeMapper;
import com.zyf.reggie.Service.EmployeeService;
import com.zyf.reggie.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl  implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public Employee employeeByName(String username) {
        Employee employee = employeeMapper.employeeByName(username);
        return employee;
    }

    @Override
    public int insertEmployee(Employee employee) {
        int i = employeeMapper.insertEmployee(employee);
        return i;
    }

    @Override
    public PageInfo<Employee> getAllEmployee(int page,int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Employee> employees = employeeMapper.selectAllEmployee();
         PageInfo<Employee> pageInfo =new PageInfo<>(employees);
        return pageInfo;
    }
    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = employeeMapper.selectById(id);

        return employee;
    }

    @Override
    public void updateEmployeeById(Employee employee) {
        employeeMapper.updateEmployee(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeMapper.update(employee);
    }
}

