package com.websitebooking.controller;

import com.websitebooking.model.User;
import com.websitebooking.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        // Lấy thông tin đăng nhập hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        // Tìm người dùng dựa trên tên đăng nhập
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("Người dùng không tồn tại!");
        }

        // Cập nhật cấp độ VIP và lưu vào cơ sở dữ liệu
        userService.updateVipLevel(user);

        // Truyền thông tin người dùng và cấp độ VIP vào model để hiển thị trên giao diện
        model.addAttribute("username", user.getUsername());
        model.addAttribute("vipLevel", user.getVipLevel());
        model.addAttribute("vipImage", getImageForVipLevel(user.getVipLevel()));

        // Trả về template "home.html"
        return "home";
    }

    public String getImageForVipLevel(String vipLevel) {
        switch (vipLevel) {
            case "Platinum":
                return "/images/VipPlatinum.jpeg"; // Đảm bảo đường dẫn này chính xác
            case "Gold":
                return "/images/VipGold.jpeg";
            case "Silver":
                return "/images/VipSilver.jpeg";
            case "Bronze":
                return "/images/VipBronze.jpeg";
            default:
                return "/images/NonVip.jpeg";
        }
    }
}
