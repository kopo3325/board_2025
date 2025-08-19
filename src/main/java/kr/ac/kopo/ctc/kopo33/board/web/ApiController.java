package kr.ac.kopo.ctc.kopo33.board.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/api/list")
    public  String list(Model model) {
        return "list";
    }
}
