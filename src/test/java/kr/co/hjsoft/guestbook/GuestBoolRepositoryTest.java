package kr.co.hjsoft.guestbook;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import kr.co.hjsoft.guestbook.entity.GuestBook;
import kr.co.hjsoft.guestbook.entity.QGuestBook;
import kr.co.hjsoft.guestbook.repository.GuestBookRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;



@SpringBootTest
public class GuestBoolRepositoryTest {
    @Autowired
    private GuestBookRepository guestBookRepository;

    //삽입 테스트
    //@Test
    public void insertGuestBook(){
        for(int i=1; i<=300; i=i+1){
            GuestBook guestBook = GuestBook.builder()
                    .title("Title..."+i)
                    .content("Content..."+i)
                    .writer("User..."+i)
                    .build();
            guestBookRepository.save(guestBook);
        }
    }
    //@Test
    public void updateGuestBook(){
            GuestBook guestBook = GuestBook.builder()
                    .gno(1L)
                    .title("샐러디")
                    .content("우삼겹웜볼")
                    .writer("노현지 배달")
                    .build();
            guestBookRepository.save(guestBook);
        }

    @Test
    public void testQuery(){
       //paging 설정
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

        //Querydsl Entity 가져오기
        QGuestBook qGuestBook = QGuestBook.guestBook;

        //조건 생성
        BooleanBuilder builder = new BooleanBuilder();
        //title에 1을 포함한 데이터를 조회
        String keyword ="1";
        BooleanExpression expression = qGuestBook.title.contains(keyword);

        //조건을 추가
        builder.and(expression);
        //검색 수행
        Page<GuestBook>result = guestBookRepository.findAll(builder, pageable);
        //출력
        for (GuestBook guestBook : result){
            System.out.println(guestBook);
        }
    }

    @Test
    //title 이나 content 에 1이 포함되어있고
    //gno의 값이 200 보다 작은 데이터 조회
    public void testSelectQuery(){
        Pageable pageable = PageRequest.of(0,10,Sort.by("gno").descending());
        QGuestBook qGuestBook = QGuestBook.guestBook;

        BooleanBuilder builder = new BooleanBuilder();

        String keyword="1";

        BooleanExpression exTitle = qGuestBook.title.contains(keyword);
        BooleanExpression exContent = qGuestBook.content.contains(keyword);
        //2개의 조건을 or 로 연결해서 추가
        builder.and(exTitle.or(exContent));
        //gno가 200보다 작은 조건
        BooleanExpression exGno = qGuestBook.gno.lt(200L);
        builder.and(exGno);

        //검색 수행
        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        //출력
        for(GuestBook guestBook : result){
            System.out.println(guestBook);
        }

    }

}
