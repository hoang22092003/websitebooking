package com.websitebooking.controller;

import com.websitebooking.Role;
import com.websitebooking.model.User;
import com.websitebooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMINSTRATOR')")
public class AdminController {
    @Autowired
    private UserService userService;

    // Hiển thị danh sách người dùng
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "admin/user-list";
    }

    // Hiển thị form chỉnh sửa vai trò người dùng
    @GetMapping("/users/{id}/edit")
    public String showEditUserRoleForm(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("roles", Role.values()); // Truyền danh sách vai trò vào view
            return "admin/edit-user-role";
        } else {
            return "redirect:/admin/users?error=UserNotFound";
        }
    }

    // Xử lý cập nhật vai trò người dùng
    @PostMapping("/users/{id}/edit")
    public String updateUserRole(@PathVariable Long id, @RequestParam("role") Role role) {
        userService.updateUserRole(id, role);
        return "redirect:/admin/users?success=RoleUpdated";
    }
}
