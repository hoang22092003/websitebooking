package com.websitebooking.controller;

import com.websitebooking.model.Booking;
import com.websitebooking.service.BookingService;
import com.websitebooking.service.HotelService;
import com.websitebooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auth/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "booking/listBooking"; // View (templates/booking/listBooking.html)
    }

    @GetMapping("/{id}")
    public String getBookingById(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        if (booking != null) {
            model.addAttribute("booking", booking);
            return "booking/detailBooking"; // View (templates/booking/detailBooking.html)
        }
        return "redirect:/auth/booking";
    }

    @GetMapping("/add")
    public String addBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "booking/addBooking"; // View (templates/booking/addBooking.html)
    }

    @PostMapping("/save")
    public String saveBooking(@ModelAttribute Booking booking) {
        bookingService.saveBooking(booking);
        return "redirect:/auth/booking";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/auth/booking";
    }
}
