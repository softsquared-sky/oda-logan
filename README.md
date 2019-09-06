# project_oda_Logan

8.25 Splash(ok), Login(80%)

8.26 Login(90%, API 연동x), Sign Up(80%, 비밀번호 확인 미구현, API 연동 x), Home(10%) 

8.27 Login(90%, API 연동x), Sign UP(90%, 중복확인 API연동, 우편번호 API로 주소 가져와야함, 가입버튼 연동 미구현), Home(10%), Search(40%, 최근검색어 ViewPager 구현, 인기검색어 API 연동 x) 

8.28 Login(API 연동 완료, jwt sSharedPreference에 담아야함), Sign Up(서버 연결 완료, 중복확인 버튼 재검토 필요)

8.29 Login(완료, 자동로그인 구현ok), Sign Up(중복확인버튼 검토 완료), Search(검색 내용 sharedPreference저장완료, x클릭시 리스트에서 사라짐ok), Main(하단 홈과 마이페이지 fragment 작동 완료, 홈 fragment에 gridview 넣어야함, 마이페이지 fragment UI작업 해야함)

8.31 기능별 패키지 분리, Home(10%)

9.2 Components name 변경, Main의 Home Fragment에 RecyclerView 적용, 각 아이템 클릭 시 상품상세내용 실행, 상품상세내용화면 뷰페이져 적용, 그 중 상품상세 API테스트 완료, 상품 리뷰 아이템 완성 어댑터 구현, 작성 API연동 남음, QNA는 화면만 만들고 API 없는것으로 하였기때문에 더미 데이터를 넣을 예정

9.4 MyPage Fragment UI(10%), Setting Activity(ok, 따로 API없음), ViewPager 속에 상품정보도 포함시켜야함, Product Detail 정보를 가져오는 service를 ProductActivity가 아니라  fragment에서 실행하는 형태로 바꾸기, Main의 바로결제 관련 service, interfaces작성해둠,  Main에서 더미 데이터 추가, 임의 상품 가져오는 service, models, interfaces 작성해둠, 오늘 건드린 UI들 다시 margin등 맞추기

9.5 ViewPager에 상품 기본 정보 이식 완료, 스크롤 잘 먹음, 상품상세 api부분들 전부 각 fragment로 서비스 분리완료, Main 전체 선택, 각 아이템 선택관련 텍스트 변경 완료, 자동 로그인  토큰의 유효성을 기준으로 작동되게 바꿈, 후기작성 액티비티 새로 만듬
    후기 작성 버튼 클릭 안먹는 현상 해결해야함, 상품상세 하단의 장바구니, 주문요청 api연동, 각 버튼 클릭시 width 들어나고 수량 선택하는 라인 추가되는 기능 구현해야함

9.6
후기 작성 버튼 해결
상품상세 하단 장바구니 API연동 완료
주문요청 버튼 기능 구현 완료
주문요청 Activity UI 완료
홈화면 전체선택 및 각 아이템 선택 구현

마이페이지 expandable list 적용 필요
홈화면의 바로결제 버튼 기능 구현 필요
인기 검색어 더미 데이터 넣기 필요
장바구니 미구현 기능들 구현 필요

