package kr.ac.kopo.guestbook.service;

import kr.ac.kopo.guestbook.dto.GuestbookDTO;
import kr.ac.kopo.guestbook.dto.PageRequestDTO;
import kr.ac.kopo.guestbook.dto.PageResultDTO;
import kr.ac.kopo.guestbook.entity.Guestbook;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import kr.ac.kopo.guestbook.repository.GustbookRepository;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {

    private final GustbookRepository repository;

    @Override
    public Long register(GuestbookDTO dto){
        Guestbook entity = dtoToEntity(dto);
        repository.save(entity);
        return entity.getGno();
    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
        Page<Guestbook> result = repository.findAll(pageable);
        Function<Guestbook, GuestbookDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }
}
