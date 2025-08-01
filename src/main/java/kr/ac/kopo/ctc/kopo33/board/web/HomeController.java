package kr.ac.kopo.ctc.kopo33.board.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
        public String home(Model model) {
        model.addAttribute("name", "kopo33");
        return  "home";
        }
}
