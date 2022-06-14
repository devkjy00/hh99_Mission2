# hh99_mission2
- [DB, API](https://teamsparta.notion.site/2-95aec72c863448c38f454734a7ccb4ce)

##
- CORS
  - [참고자료](https://yeonyeon.tistory.com/m/236)

## 오류
- User 테이블을 생성할 때 user 키워드가 이미 DB에서 사용중이라서 테이블을 만들지 못하는 오류가 발생
  - @Table(name = "user_table") 어노테이션을 추가해서 테이블 이름을 변경했다
  
