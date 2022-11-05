package com.zyf.reggie.Service;

import com.github.pagehelper.PageInfo;
import com.zyf.reggie.pojo.Employee;


public interface EmployeeService {
    Employee employeeByName(String username);
    int insertEmployee(Employee employee);
    PageInfo<Employee> getAllEmployee(int page,int pageSize);
    Employee getEmployeeById(int id);
    void updateEmployeeById(Employee employee);
    void update(Employee employee);
}
