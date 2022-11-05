package com.zyf.reggie.Mappers;


import com.zyf.reggie.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
      Employee employeeByName(@Param("username") String username);
      int  insertEmployee(@Param("employee") Employee employee);
      List<Employee> selectAllEmployee();
      Employee selectById(@Param("id") Integer id);
      void updateEmployee(@Param("employee") Employee employee);
      void update(@Param("employee") Employee employee);

}
