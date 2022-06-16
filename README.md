# hh99_mission2
- [DB, API](https://teamsparta.notion.site/2-95aec72c863448c38f454734a7ccb4ce)

##
- CORS
  - [참고자료](https://yeonyeon.tistory.com/m/236)

## Log
- ***User 테이블을 생성할 때 user 키워드가 이미 DB에서 사용중이라서 테이블을 만들지 못하는 오류가 발생***
  - @Table(name = "user_table") 어노테이션을 추가해서 테이블 이름을 변경했다
  
- ***@Valid로 Body값 유효성 검사를 할 때 message값으로 enum을 사용할 수 없었다***
  - DtoMessage 클래스안에 상수로 문자열을 정의해서 사용했다

- ***@Valid에서 매개변수로 받은 Errors를 처리하려고 했는데 매개변수가 늘어나고 코드가 더러워진다***
  - @RestControllerAdvice와 @ExceptionHandler를 사용해서 분리했다

- ***Like테이블 생성할 때 Error executing DDL 오류가 발생***
  - [*구글링*](https://csy7792.tistory.com/66) 을 해본 해본결과 like라는 이름은 예약어 이기 때문에 컬럼명으로 사용할 수 없다, like -> like_status로 변경
  
- ***좋아요 기능을 구현하고 테스트하는데 서버에서는 아무런 오류가 없는데 포스트맨에서 404에러가 발생한다***
  - @RestController가 아닌 @Controller로 잘못 입력해서 발생한 문제.....ㅠㅠ... 작성할 때 잘 확인하자

- ***클라이언트로 부터 입력받지 못한 Entity의 속성 값이 null이 되는 상황***
  - @ColumnDefalut 컬럼의 디폴트 값을 명시, @DynamicInsert 값이 null인 컬럼은 빼고 insert 자동으로 디폴트 값이 저장된다
