package kr.ac.kopo.board.service;

import kr.ac.kopo.board.DTO.ReplyDTO;
import kr.ac.kopo.board.entity.Board;
import kr.ac.kopo.board.entity.Member;
import kr.ac.kopo.board.entity.Reply;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReplyService {
//    댓글 등록 기능
    Long register(ReplyDTO replyDTO);
//    댓글 수정 기능
    void modify(ReplyDTO replyDTO);
//    댓글 삭제 기능
    void remove(Long rno);
//    댓글 목록 반환 기능
    List<ReplyDTO> getList(Long bno);

//    Entity를 DTO로 변환하는 메소드
    default ReplyDTO entityToDTO(Reply reply){
        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .bno(reply.getBoard().getBno())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
        return replyDTO;
    }

//    DTO를 Entity로 변환하는 메소드(
    default Reply dtoToEntity(ReplyDTO dto){
        Board board = Board.builder()
                .bno(dto.getBno())
                .build();

        Reply reply = Reply.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replyer(dto.getReplyer())
                .board(board)
                .build();

        return reply;
    }
}
