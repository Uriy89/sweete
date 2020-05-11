package main.controller;

import main.model.Message;
import main.repo.MessagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private MessagesRepo messagesRepo;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String home(Map<String, Object> model) {
        Iterable<Message> messages = messagesRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messagesRepo.save(message);
        Iterable<Message> messages = messagesRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filter")
    public String search(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if(filter != null && !filter.isEmpty()) {
            messages = messagesRepo.findByTag(filter);
        } else {
            messages = messagesRepo.findAll();
        }
        model.put("messages", messages);
        return "main";
    }
}
