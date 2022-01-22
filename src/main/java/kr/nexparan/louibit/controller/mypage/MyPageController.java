package kr.nexparan.louibit.controller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @GetMapping("/history")
    public String history() {
        return "mypage/history";
    }

    @GetMapping("/referral")
    public String referral() {
        return "mypage/referral";
    }

    @GetMapping("/fee")
    public String fee() {
        return "mypage/fee";
    }
}
