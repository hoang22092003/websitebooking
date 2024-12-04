package com.websitebooking.controller;

import com.websitebooking.model.Hotel;
import com.websitebooking.model.User;
import com.websitebooking.service.HotelService;
import com.websitebooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auth/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    private final UserService userService;

    public HotelController(UserService userService) {
        this.userService = userService;
    }

    // Hiển thị danh sách tất cả các khách sạn
    @GetMapping("/listHotel")
    public String getAllHotels(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);
        return "hotels/listHotel"; // Tên file view (templates/hotels/list.html)
    }

    // Hiển thị chi tiết một khách sạn
    @GetMapping("/search/{id}")
    public String getHotelById(@PathVariable Long id, Model model) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel != null) {
            model.addAttribute("hotel", hotel);
            return "hotels/detail"; // Tên file view (templates/hotels/detail.html)
        }
        return "redirect:/auth/hotels/listHotel";
    }

    // Form để thêm mới khách sạn
    @PreAuthorize("hasAnyRole('ADMIN', 'ADMINISTRATOR')")
    @GetMapping("/add")
    public String addHotelForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "hotels/addHotel"; // Tên file view (templates/hotels/add.html)
    }

    // Xử lý việc thêm mới hoặc cập nhật khách sạn
    @PreAuthorize("hasAnyRole('ADMIN', 'ADMINISTRATOR')")
    @PostMapping("/save")
    public String saveHotel(@ModelAttribute Hotel hotel) {
        hotelService.saveHotel(hotel);
        return "redirect:/auth/hotels/listHotel";
    }

    // Xóa khách sạn
    @PreAuthorize("hasAnyRole('ADMIN', 'ADMINISTRATOR')")
    @GetMapping("/delete/{id}")
    public String deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return "redirect:/auth/hotels/listHotel";
    }

    // Cập nhật khách sạn
    @PreAuthorize("hasAnyRole('ADMIN', 'ADMINISTRATOR')")
    @GetMapping("/edit/{id}")
    public String editHotelForm(@PathVariable Long id, Model model) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel != null) {
            model.addAttribute("hotel", hotel);
            return "hotels/editHotel";  // Form sửa thông tin khách sạn
        }
        return "redirect:/auth/hotels/listHotel";  // Chuyển hướng nếu không tìm thấy khách sạn
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'ADMINISTRATOR')")
    @PostMapping("/update/{id}")
    public String updateHotel(@PathVariable Long id, @ModelAttribute Hotel hotel) {
        hotel.setId(id);  // Đảm bảo ID được thiết lập khi cập nhật
        hotelService.saveHotel(hotel);  // Cập nhật khách sạn
        return "redirect:/auth/hotels/listHotel";  // Chuyển hướng về danh sách khách sạn
    }

    // Tìm kiếm khách sạn
    @GetMapping("/search")
    public String searchHotels(@RequestParam String hotelName,
                               @RequestParam String location,
                               @RequestParam double minPrice,
                               @RequestParam double maxPrice,
                               @RequestParam(required = false) String amenities,
                               Model model) {
        List<Hotel> hotels = hotelService.searchHotels(hotelName, location, minPrice, maxPrice, amenities);
        model.addAttribute("hotels", hotels);
        return "hotels/listHotel";
    }

    @GetMapping("/homeHotel")
    public String homeHotel(Model model) {
        // Gửi dữ liệu mẫu (nếu cần)
        model.addAttribute("title", "Trang chủ khách sạn");
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
        return "hotels/homeHotel";
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
