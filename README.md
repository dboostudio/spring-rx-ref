## CH2

### spring-reactive
- Mono, Flux
- map, flatmap, defaultIfEmpty

### spring-data-mongo-db
- CrudRepository
- MethodQuery
- ExampleQuery
- @Query
- fluentOperation

## CH3

### spring dev tools
- 애플리케이션 재시작 restart과 리로드reload 자동화
  - third-party 라이브버리를 종료하지 않고, 사용자 코드를 로딩했던 클래스 로더만 새로 시작하여 재시작 시간을 단축
  - JRebel과 같은 자바 에이전트 솔루션 사용시 효과 극대화 할 수 있음
  - 재시작 트리거로는 IDE에서 save, build등을 사용할 수 있음
- 환경설정 정보 기본값 제공
- 자동설정autoconfiguration 변경사항 로깅
- 정적 자원 제외
- 라이브 리로드LiveReload 지원