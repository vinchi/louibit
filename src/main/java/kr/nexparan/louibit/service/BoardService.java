package kr.nexparan.louibit.service;

import kr.nexparan.louibit.model.Board;
import kr.nexparan.louibit.model.User;
import kr.nexparan.louibit.repository.BoardRepository;
import kr.nexparan.louibit.repository.ReplyRepository;
import kr.nexparan.louibit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public Board save(String email, Board board) {
        User user = userRepository.findByEmail(email);
        board.setUser(user);
        board.setContent(board.getContent().replace("\r\n", "<br />"));
        return boardRepository.save(board);
    }
}
