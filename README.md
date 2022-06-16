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
