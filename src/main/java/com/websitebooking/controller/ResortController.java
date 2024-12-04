package com.websitebooking.controller;

import com.websitebooking.model.Hotel;
import com.websitebooking.model.Resort;
import com.websitebooking.service.ResortService;
import com.websitebooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auth/resorts")
public class ResortController {

    @Autowired
    private ResortService resortService;

    private final UserService userService;

    public ResortController(UserService userService) {
        this.userService = userService;
    }

    // Hiển thị danh sách tất cả các resort
    @GetMapping("/listResort")
    public String getAllResorts(Model model) {
        List<Resort> resorts = resortService.getAllResorts();
        model.addAttribute("resorts", resorts);
        return "resorts/listResort"; // Tên file view (templates/resorts/list.html)
    }

    // Hiển thị chi tiết một resort
    @GetMapping("/search/{id}")
    public String getResortById(@PathVariable Long id, Model model) {
        Resort resort = resortService.getResortById(id);
        if (resort != null) {
            model.addAttribute("resort", resort);
            return "resorts/resortDetails"; // Tên file view (templates/resorts/detail.html)
        }
        return "redirect:/resorts";
    }

    // Form để thêm mới resort
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMINISTRATOR')")
    @GetMapping("/add")
    public String addResortForm(Model model) {
        model.addAttribute("resort", new Resort());
        return "resorts/add"; // Tên file view (templates/resorts/add.html)
    }

    // Xử lý việc thêm mới hoặc cập nhật resort
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMINISTRATOR')")
    @PostMapping("/save")
    public String saveResort(@ModelAttribute Resort resort) {
        resortService.saveResort(resort);
        return "redirect:/resorts";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'ADMINISTRATOR')")
    @PostMapping("/update/{id}")
    public String updateResort(@PathVariable Long id, @ModelAttribute Resort resort) {
        resort.setId(id);  // Đảm bảo ID được thiết lập khi cập nhật
        resortService.saveResort(resort);  // Cập nhật resort
        return "redirect:/auth/resorts/listResort";  // Chuyển hướng về danh sách resort
    }

    // Xóa resort
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMINISTRATOR')")
    @GetMapping("/delete/{id}")
    public String deleteResort(@PathVariable Long id) {
        resortService.deleteResort(id);
        return "redirect:/resorts";
    }

    // Tìm kiếm resort
    @GetMapping("/search")
    public String searchResorts(@RequestParam String resortName,
                                @RequestParam String location,
                                @RequestParam double minPrice,
                                @RequestParam double maxPrice,
                                @RequestParam(required = false) String amenities,
                                Model model) {
        List<Resort> resorts = resortService.searchResorts(resortName, location, minPrice, maxPrice, amenities);
        model.addAttribute("resorts", resorts);
        return "resorts/listResort"; // Chuyển đến view danh sách resort
    }

    @GetMapping("/homeResort")
    public String homeResort(Model model) {
        model.addAttribute("title", "Trang chủ resort");
        return "resorts/homeResort";
    }
    public String getImageForVipLevel(String vipLevel) {
        switch (vipLevel) {
            case "Platinum":
                return "/images/VipPlatinum.jpeg";
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
