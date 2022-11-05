package com.zyf.reggie.Controller;

import com.github.pagehelper.PageInfo;
import com.zyf.reggie.Service.CategoryService;
import com.zyf.reggie.common.R;
import com.zyf.reggie.pojo.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
@GetMapping("/page")
public R<PageInfo> page(int page,int pageSize){
    PageInfo<Category> pageInfo = categoryService.selectAll(page, pageSize);
    return R.success(pageInfo);
}
@PostMapping
public R<String> addCategory(HttpServletRequest request, @RequestBody Category category){
    Random random =new Random();
    Long empId = (Long) request.getSession().getAttribute("employee");
    Date date=new Date();
    category.setId(random.nextLong());
    category.setCreateTime(date);
    category.setUpdateTime(date);
    category.setCreateUser(empId);
    category.setUpdateUser(empId);
    categoryService.insertById(category);
    return R.success("新增成功");
}
@DeleteMapping
public R<String> delete(Long ids){
    log.info(ids.toString());
   categoryService.delete(ids);
    return R.success("删除成功");
}
@PutMapping
public R<String> update(@RequestBody Category category){
    log.info(category.toString());
    categoryService.update(category);
    return R.success("修改成功");
}
@GetMapping("/list")
public R<List<Category>> list(Integer type){
    List<Category> list=null;
    if (type!=null){
     list = categoryService.list(type);

    }
    list = categoryService.selectAllTo();

    return R.success(list);
}
}
