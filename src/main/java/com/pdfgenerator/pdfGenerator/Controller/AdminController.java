package com.pdfgenerator.pdfGenerator.Controller;

import com.pdfgenerator.pdfGenerator.Entity.BookingEntity;
import com.pdfgenerator.pdfGenerator.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(value = "*")
public class AdminController {
    @Autowired
    public AdminService adminService;

    @GetMapping("/adminview")
    public List<BookingEntity> getDetails(){
        return adminService.getDetails();
    }

    @PutMapping("/adminview/{userId}")
    public void updateStatus(@PathVariable Long userId, @RequestBody String status){
            adminService.updateStatus(status,userId);
    }


    @RequestMapping(method=RequestMethod.DELETE, value="/admin/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
    }
}
