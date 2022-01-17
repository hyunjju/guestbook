package kr.co.hjsoft.guestbook.service;

import kr.co.hjsoft.guestbook.dto.GuestBookDTO;
import kr.co.hjsoft.guestbook.dto.PageRequestDTO;
import kr.co.hjsoft.guestbook.dto.PageResponseDTO;
import kr.co.hjsoft.guestbook.entity.GuestBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface GuestBookService {
    //DTO 클래스의 인스턴스를 Entity 인스턴스로 변환해주는 메소드
    default GuestBook dtoToEntity(GuestBookDTO dto){
        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    //Entity 클래스의 인스턴스를 DTO 클래스의 인스턴스로 변환해주는 메소드
    default GuestBookDTO entityToDTO(GuestBook entity){
        GuestBookDTO dto = GuestBookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }

   public PageResponseDTO<GuestBookDTO, GuestBook> getList(
            PageRequestDTO requestDTO);
    //데이터 삽입

    //상게보기를 위한 메소드
    public GuestBookDTO read(Long gno);

}
