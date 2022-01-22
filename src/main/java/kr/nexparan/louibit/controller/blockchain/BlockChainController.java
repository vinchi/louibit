package kr.nexparan.louibit.controller.blockchain;

import kr.nexparan.louibit.model.User;
import kr.nexparan.louibit.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/blockchain")
public class BlockChainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/spot")
    public String spot(Model model, Authentication authentication) {
        if(authentication == null) {

        } else {
            String username = authentication.getName();
            User user = userRepository.findByEmail(username);
            log.debug("username : {}", username);
            model.addAttribute("user", user);

        }
        return "blockchain/blockchain";
    }

    @GetMapping("/futures")
    public String futures(Model model, Authentication authentication) {
        if(authentication == null) {

        } else {
            String username = authentication.getName();
            User user = userRepository.findByEmail(username);
            log.debug("username : {}", username);
            model.addAttribute("user", user);

        }
        return "blockchain/blockchain";
    }
}
