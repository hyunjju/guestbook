package kr.co.hjsoft.guestbook;

import kr.co.hjsoft.guestbook.dto.GuestBookDTO;
import kr.co.hjsoft.guestbook.dto.PageRequestDTO;
import kr.co.hjsoft.guestbook.dto.PageResponseDTO;
import kr.co.hjsoft.guestbook.entity.GuestBook;
import kr.co.hjsoft.guestbook.service.GuestBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class GuestBookServiceTest {
    @Autowired
    private GuestBookService service;

   // @Test
    public void registerTest(){
        GuestBookDTO dto = GuestBookDTO.builder().title("제목").content("내용").writer("쮸").build();

        Long gno = service.register(dto);
        System.out.println(gno);
    }

    @Test
    public void listTest() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        PageResponseDTO<GuestBookDTO, GuestBook>
                PageResponseDTO = service.getList(pageRequestDTO);
        for (GuestBookDTO guestbookDTO : PageResponseDTO.getDtoList()) {
            System.out.println(dto);

        }

        //이전 과 다음 링크 여부와 전체 페이지 개수 확인
        System.out.println("PREV: "+ pageResponseDTO.isPrev());
        System.out.println("NEXT: "+ pageResponseDTO.isNext());
        System.out.println("TOTAL: " + pageResponseDTO.getTotalPage());
        //페이지 번호 목록 출력
        System. out. println ("-------------------------------------------------")
        for(Integer i : PageResponseDTO.getPageList()){
           System.out.println(i+"\t");
        }
    }
}
