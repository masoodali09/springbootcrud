
// Importing packages modules
package com.masoodali09.springbootdemoproject.department.controller;// Java Program to Illustrate DepartmentController.java File


// Importing required classes

import java.net.URI;
import java.util.List;
import com.masoodali09.springbootdemoproject.department.entity.Department;
import com.masoodali09.springbootdemoproject.department.service.DepartmentService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Annotation
@RestController
// Class
public class DepartmentController {

    @Autowired private DepartmentService departmentService;
//test

    public static final String ACCOUNT_SID = "AC5d61f8e4be76ea62b60d293abf000e16";
    public static final String AUTH_TOKEN = "293b5a3d5566620dcdc86c1d29226663";
    // Save operation
    @PostMapping("/departments")

    public Department saveDepartment(
             @RequestBody Department department)
    {
        return departmentService.saveDepartment(department);
    }

    // Read operation
    @GetMapping("/departments")

    public List<Department> fetchDepartmentList()
    {
        return departmentService.fetchDepartmentList();
    }

    // Update operation
    @PutMapping("/departments/{id}")

    public Department
    updateDepartment(@RequestBody Department department,
                     @PathVariable("id") Long departmentId)
    {
        return departmentService.updateDepartment(
                department, departmentId);
    }

    @GetMapping("/twilio")

    public String twilio()
    {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Call call = Call.creator(new com.twilio.type.PhoneNumber("+13365305464"),
                        new com.twilio.type.PhoneNumber("+13365715930"),
                        URI.create("http://demo.twilio.com/docs/voice.xml"))
                .create();

        System.out.println(call.getSid());
        return call.getSid();
    }

    // Delete operation
    @DeleteMapping("/departments/{id}")

    public String deleteDepartmentById(@PathVariable("id")
                                       Long departmentId)
    {
        departmentService.deleteDepartmentById(
                departmentId);
        return "Deleted Successfully";
    }
}
