package kr.ac.kopo.board.service;

import kr.ac.kopo.board.DTO.ReplyDTO;
import kr.ac.kopo.board.entity.Board;
import kr.ac.kopo.board.entity.Reply;
import kr.ac.kopo.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
    private final ReplyRepository replyRepository;
    //    댓글 등록 기능
    public Long register(ReplyDTO replyDTO){
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
        return reply.getRno();
    }
    //    댓글 수정 기능
    public void modify(ReplyDTO replyDTO){
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);

    }
    //    댓글 삭제 기능
    public void remove(Long rno){
        replyRepository.deleteById(rno);
    }
    //    댓글 목록 반환 기능
    public List<ReplyDTO> getList(Long bno){
        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(Board.builder().bno(bno).build());
        List<ReplyDTO> replyDTOList = replyList.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());
        return replyDTOList;
    }
}
