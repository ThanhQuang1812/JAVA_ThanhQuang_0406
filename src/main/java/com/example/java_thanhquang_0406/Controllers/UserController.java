package com.example.java_thanhquang_0406.Controllers;

import com.example.java_thanhquang_0406.Entities.Role;
import com.example.java_thanhquang_0406.Entities.User;
import com.example.java_thanhquang_0406.Repositories.RoleRepository;
import com.example.java_thanhquang_0406.Services.RoleService;
import com.example.java_thanhquang_0406.Services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RequiredArgsConstructor

@Controller
public class UserController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "User/User-list";
    }


    @GetMapping("/users/add")
    public String showAddForm(Model model) {
        model.addAttribute("users", new User());
        model.addAttribute("role", roleService.getAllRole());
        return "User/add-user";
    }

    @PostMapping("/users/add")
    public String addUser(@Valid User user, BindingResult result, @RequestParam("roles") Long roleId) {
        if (result.hasErrors()) {
            return "User/add-user";
        }
        Role role = roleService.getRoleById(roleId).orElseThrow(() -> new IllegalArgumentException("Role not found"));
        ; // Lấy thông tin của vai trò từ ID
        user.setRole(role);
        userService.addUser(user);
        return "redirect:/users";
    }


    @GetMapping("/users/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "User/update-user";
    }

    // POST request to update category
    @PostMapping("/users/update/{id}")
        public String updateUser(@PathVariable("id") String id, @Valid User user, BindingResult result, Model model) {
        userService.updateUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
            User  user      = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:"+ id));
            userService.deleteUserById(id);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/users";
    }
}
