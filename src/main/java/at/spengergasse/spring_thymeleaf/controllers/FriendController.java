package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Friend;
import at.spengergasse.spring_thymeleaf.entities.FriendRepository;
import jakarta.persistence.Column;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/friend")
public class FriendController {
    private final FriendRepository friendRepository;

    public FriendController(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @GetMapping("/list")
    public String friends(Model model) {
        model.addAttribute("friends", friendRepository.findAll());
        return "friendlist";
    }

    @GetMapping("/add")
    public String addFriend(Model model) {
        model.addAttribute("friend", new Friend());
        return "add_friend";
    }


    @PostMapping("/add")
    public String addFriend(@ModelAttribute("friend") Friend friend) {
        friendRepository.save(friend);
        return "redirect:/friend/list";
    }

    @PostMapping("/deleteFriend/{id}")
    public String deleteFriend(@PathVariable int id) {
        friendRepository.deleteById(id);
        return "redirect:/friend/list";
    }


}
