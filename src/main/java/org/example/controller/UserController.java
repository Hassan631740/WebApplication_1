package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        logger.info("Fetching all users");
        model.addAttribute("users", userService.getAllUsers());
        return "user-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        logger.info("Showing form for adding new user");
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result,
                           RedirectAttributes redirectAttributes) {
        logger.info("Attempting to save user: {}", user.getFirstName());
        if (result.hasErrors()) {
            logger.warn("Validation errors found: {}", result.getAllErrors());
            return "user-form"; // Return to form with errors
        }

        try {
            userService.saveUser(user);
            logger.info("User saved successfully: {}", user.getId());
            redirectAttributes.addFlashAttribute("message", "User saved successfully!");
        } catch (Exception e) {
            logger.error("Error saving user: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Failed to save user. Please try again.");
            return "redirect:/users/showFormForAdd";
        }
        return "redirect:/users";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") Long id, Model model) {
        logger.info("Showing form for updating user with id: {}", id);
        User user = userService.getUserById(id);
        if (user == null) {
            logger.warn("User not found with id: {}", id);
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "user-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long id, RedirectAttributes redirectAttributes) {
        logger.info("Attempting to delete user with id: {}", id);
        try {
            userService.deleteUser(id);
            logger.info("User deleted successfully: {}", id);
            redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        } catch (Exception e) {
            logger.error("Error deleting user: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Failed to delete user. Please try again.");
        }
        return "redirect:/users";
    }

    @GetMapping("/")
    public String redirectToUsers() {
        return "redirect:/users";
    }
}