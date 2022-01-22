package kr.nexparan.louibit.controller.board;

import kr.nexparan.louibit.model.Board;
import kr.nexparan.louibit.model.Faq;
import kr.nexparan.louibit.model.Reply;
import kr.nexparan.louibit.repository.BoardRepository;
import kr.nexparan.louibit.repository.FaqRepository;
import kr.nexparan.louibit.repository.ReplyRepository;
import kr.nexparan.louibit.service.BoardService;
import kr.nexparan.louibit.service.FaqService;
import kr.nexparan.louibit.validator.BoardValidator;
import kr.nexparan.louibit.validator.FaqValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardValidator boardValidator;
    @Autowired
    private BoardService boardService;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private FaqRepository faqRepository;
    @Autowired
    private FaqValidator faqValidator;
    @Autowired
    private FaqService faqService;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false, defaultValue="") String searchText) {
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = 1;
        int endPage = boards.getTotalPages() == 0 ? 1 : boards.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/boardForm")
    public String boardForm(Model model, @RequestParam(required = false) Long id) {
        if(id == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/boardForm";
    }

    @PostMapping("/boardForm")
    public String postNoticeForm(@Valid Board board, BindingResult bindingResult, Authentication authentication) {
        boardValidator.validate(board, bindingResult);
        if(bindingResult.hasErrors()) {
            return "noticeForm";
        }
        String username = authentication.getName();
        boardService.save(username, board);
        return "redirect:/board/list";
    }

    @GetMapping("/boardView")
    public String boardView(Model model, @RequestParam(required = true) Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        List<Reply> replys = replyRepository.findByBoardId(id);
        board.setReplys(replys);
        model.addAttribute("board", board);
        return "board/boardView";
    }

    @GetMapping("/faqs")
    public String faq(Model model, @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false, defaultValue="") String searchText) {
        Page<Faq> faqs = faqRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = 1;
        int endPage = faqs.getTotalPages() == 0 ? 1 : faqs.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("faqs", faqs);
        return "board/faqs";
    }

    @GetMapping("/faqsForm")
    public String faqsForm(Model model, @RequestParam(required = false) Long id) {
        if(id == null) {
            model.addAttribute("faq", new Faq());
        } else {
            Faq faq = faqRepository.findById(id).orElse(null);
            model.addAttribute("faq", faq);
        }
        return "board/faqsForm";
    }

    @PostMapping("/faqsForm")
    public String faqForm(@Valid Faq faq, BindingResult bindingResult) {
        faqValidator.validate(faq, bindingResult);
        if(bindingResult.hasErrors()) {
            return "faqForm";
        }
        faqService.save(faq);
        return "redirect:/board/faqs";
    }

    @GetMapping("/contacts")
    public String contacts() {
        return "board/contacts";
    }

}
