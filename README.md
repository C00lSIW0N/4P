# 4P
주의사항 1.
빠른 확인을 위한 로그인 시 시연 아이디
id : user1@user.com
pwd : 111111

주의사항 2.
최종 결과물인 앱에 MAINACTIVITY에서 GPT api키를 입력하는 부분이 있는데, 무료 api키 특성상 앱 사용중 key 오류가 뜨면 
open ai에서 자신의 api키를 발급받고 키를 해당부분에 입력하여 수정 후 재실행 필요.
(유료 api키를 사용한다면 한번 수정 후 추가 수정없이 사용가능)

주의사항 3.
시연웹사이트가 localhost 주소이기 때문에 앱과 시연웹사이트 모두 같은 네트워크(와이파이) 상에 있어야하며
리액트서버(localhost) 실행 후 앱 시연 필요.

주의사항 4. 
에뮬레이터에서 localhost링크를 불러오는 방법과 실제 안드로이드폰에서 localhost링크를 불러오는 방법이 다름,
깃허브에 소스코드는 실제 안드로이드폰에서 불러오는 방법으로 구현되어 있음. 앱에 MAINACTIVITY에서 
localhost를 링크를 불러오는 부분에 현재 리액트서버를 실행하는 pc에 ip를 입력하여 수정 후 실행.
// http://223.194.131.165 부분과 http://223.194.157.156 부분
(에뮬레이터로 실행 시 링크의 ip주소 부분을 10.0.2.2 로 모두 수정) 
