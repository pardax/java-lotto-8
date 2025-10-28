# java-lotto-precourse

### 기능 요구사항
1. 로또의 숫자범위는 1~45
2. 중복되지 않는 숫자 7개 추첨
3. 정수 n을 입력받아 n/1000번 시도
4. 당첨번호와 보너스번호를 입력받는다
5. 잘못된 값 인식시 IllegalArgumentException을 발생하고 다시 입력을 받음, exption이 아닌 IllegalArgumentException, IllegalStateException을 통해 명확한 유형을 처리
6. 제공된 Lotto 클래스를 활용하여 구현, 인스턴스 변수 추가 금지


### 프로그래밍 요구사항
1. 자바 코드 컨벤션 준수, System.exit() 호출 금지
2. indent depth 2이하
3. JUnit, AssertJ 테스트 코드 활용
4. 함수의 길이가 15라인을 넘지 않기 및 다기능 x
5. 예약어 사용금지
6. Enum을 기반으로 하기
7. camp.nextstep.edu.missionutils 라이브러리 사용


### 비고
1. 실행 결과 예시의 템플릿 준수
2. 당첨 통계에 수익률 추가, (모든 당첨금 합계 / 총 구입 금액) × 100


### 기능 구현 목록
- [x] State Enum, Prize Enum 초기화
- [ ] n을 입력받고 n/1000만큼의 번호 추첨하는 기능 구현
- [ ] 당첨번호, 보너스번호를 입력받고 해당 번호 무결성 검사하는 기능 구현
- [ ] 잘못된 번호 입력 시 에러 출력 후 해당 State부터 재입력을 받는 기능 구현
- [ ] 당첨여부를 확인 및 수익률 출력 프로세스 구현