package kr.nexparan.louibit.controller.board;

import kr.nexparan.louibit.dto.ResponseDto;
import kr.nexparan.louibit.model.Board;
import kr.nexparan.louibit.model.Reply;
import kr.nexparan.louibit.model.User;
import kr.nexparan.louibit.repository.BoardRepository;
import kr.nexparan.louibit.repository.ReplyRepository;
import kr.nexparan.louibit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardApiController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @GetMapping("/boards")
    List<Board> all(@RequestParam(required = false) String title, @RequestParam(required = false, defaultValue = "") String content) {
        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return boardRepository.findAll();
        } else {
            return boardRepository.findByTitleOrContent(title, content);
        }
    }

    @Transactional
    @PostMapping("/board")
    Board newBoard(@RequestBody Board newBoard) {
        return boardRepository.save(newBoard);
    }

    @GetMapping("/board/{id}")
    Board one(@PathVariable Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    @Transactional
    @PutMapping("/board/{id}")
    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id) {
        return boardRepository.findById(id)
                .map(board -> {
                    board.setTitle(newBoard.getTitle());
                    board.setContent(newBoard.getContent());
                    return boardRepository.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return boardRepository.save(newBoard);
                });
    }

    @DeleteMapping("/board/{id}")
    void deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
    }

    @PostMapping("/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@PathVariable Long boardId, @RequestBody Reply reply, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByEmail(username);
        Board board = boardRepository.findById(boardId).orElse(null);
        reply.setUser(user);
        reply.setBoard(board);
        replyRepository.save(reply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
