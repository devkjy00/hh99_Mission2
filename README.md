# Mission2
- [API](https://teamsparta.notion.site/2-95aec72c863448c38f454734a7ccb4ce)
## 프레임워크와 라이브러리의 차이점
- ***제어 반전*** 
  -  프레임워크는 스스로 제어를 하고 사용자의 코드흐름을 강제, 라이브러리는 사용자가 직접 제어해서 코드를 작성한다

## 예외처리 작업
- ***사용자 예외 클래스, 상수 문자열, enum 클래스, @Valid, @Validated***
  - 사용자 예외 클래스를 만들어서 예외를 처리하도록 만들었다
  - 예외 메시지로 상수 문자열로 정의했고 enum 클래스로 Http 상태코드와 메시지를 정의해서 처리했다
  - @Valid, @Validated로 Dto클래스로 들어온 값들을 검사하도록 작성했다

## Entity 설계, 연관관계
- Entity설계를 위해 먼저 필요한 기능들(API)를 정리했고 기능에 필요한 값들을 테이블에 저장하기 위해 Entity로 만들었다
- 연관관계
  - 연관관계는 테이블간의 관계로 1:1, 1:N, N:1, N:M 관계가 있다
  - A테이블의 기본키를 B테이블의 외래키로 사용함으로써 연관관계를 정의하고 JOIN연산을 통해서 같은 키값을 가진 튜플들에 접근 할 수 있다
  - 주로 하나의 개체의 속성값이 다중값인 경우에 테이블을 분리하고 외래키를 저장해서 관계를 정의한다

## Restful
- Restful이란 API 설계의 중심에 자원(Resource)이 있고 HTTP Method 를 통해 자원을 처리하도록 설계하는 것
  - 자원(Resource) : URI
  - 행위(Verb) : HTTP 메소드(GET, POST, PUT, DELETE)

- Rest API가 필요한 이유 *"범용성"*

- 장점 
  1. 사용하기 편하고 Open API 를 제공하기 쉽다
  2. 멀티플랫폼 지원 및 연동이 용이하다
  3. 원하는 타입으로 데이터를 주고 받을 수 있다
  4. 기존 웹 인프라(HTTP)를 그대로 사용할 수 있다

- 단점
  1. 사용할 수 있는 메소드가 4 가지 밖에 없다
  2. 표준이 되는 디자인 가이드가 존재하지 않는다
  3. HTTP 통신 모델에 대해서만 지원한다

- 프로젝트에서 Rest API를 만들기 위해 URI로 자원을 표현하고 4개의 HTTP메소드를 사용했다


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

- ***Integer값에 @NotBlank를 사용해서 UnexpectedTypeException 발생***
  - @NotNull로 교체

- ***@valid로 Dto의 모든 값의 유효성을 검사하면 테이블의 몇개의 값만 수정하고 싶을 때 null을 받을 수 없다***
  - [@Validated로 그룹핑을 해서 유효성 검사를 했다](https://velog.io/@damiano1027/Spring-Valid-Validated%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EC%9C%A0%ED%9A%A8%EC%84%B1-%EA%B2%80%EC%A6%9D)
