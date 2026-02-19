package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Friend;
import at.spengergasse.spring_thymeleaf.entities.FriendRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/friend")
public class FriendController {
    private final FriendRepository friendRepository;

    public FriendController(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @GetMapping("/list")
    public String patients(Model model) {
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
        return  "redirect:/friend/list";
    }
}
