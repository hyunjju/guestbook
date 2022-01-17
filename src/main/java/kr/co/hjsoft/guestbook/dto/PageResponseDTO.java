package kr.co.hjsoft.guestbook.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//출력할 내용을 가지는 DTO
@Data
//DTO 는 출력할 DTO 클래스이고 EN 은 Entity 클래스
public class PageResponseDTO<DTO, EN> {
    //DTO 리스트
    private List<DTO>dtoList;

    //전체 페이지 개수
    private int totalPage;

    //현재 페이지 번호
    private int page;
    //출력할 페이지당 목록 개수
    private int size;

    //시작하는 페이지 번호
    private int start;
    //끝나는 페이지 번호
    private int end;

    //이전 다음 링크 부여
    private boolean prev;
    private boolean next;

    //페이지 번호 목록
    private List<Integer> pageList;

    //페이지 번호 목록 만드는 메소드
    private void makePageList(Pageable pageable){
        this.page=pageable.getPageNumber()+1;
        this.size=pageable.getPageSize();

        int tempEnd = (int)(Math.ceil(page/10.0))*10;
        start=tempEnd-9;

        prev=start>1;
        end=totalPage>tempEnd?tempEnd:totalPage;
        next=totalPage>tempEnd;

        pageList=new ArrayList<>();
        for(int i=start; i<=end; i=i+1){
            pageList.add(i);
        }
    }

    //Page 의 내용을 가지고 dtoList 로 만들어주는 메소드
    public PageResponseDTO(Page<EN> result, Function<EN, DTO> fn){
        //stream 는 순회
        //map 은 변환
        //collect 는 Collection 을 생성해주는 메소드
        dtoList=result.stream()
                .map(fn)
                .collect(Collectors.toList());

        //전체 페이지 개수 가져오기
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }
}
