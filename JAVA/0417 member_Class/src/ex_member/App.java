package ex_member;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        //3개의 인스턴스(object)를 생성
        //3개의 동일 오브젝트를 저장하기 위한 저장 객체를 만들어야 함
        //배열을 이용함
        //배열의 선언 방식: 자료형[] 변수명;
        //배열의 선언-생성 동시 : 자료형[] 변수명 = new 자료령[크기];
        //자료형 --> 방금 선언한 member의 구조에 따라 만들어야 한다.
        //자료형 --> member
        member[] _dbmember = new member[3];
        //각 인스턴스 수행
        _dbmember[0] = new member("1111", "홍길동", "21",)
        _dbmember[1] = new member();
        _dbmember[2] = new member();
        //사용자가 검색할 수 있도록 인터페이스를 제공
        System.out.printf("검색어 입력(학번): ");
        Scanner sc = new Scanner(System.in); //스캐너 등록
        String keyword = sc.nextLine(); //입력 처리
        //검색 기능을 처리하고, 해당 결과를 출력
        //검색 기능은 membeer 클래스에 정의된 객체 검색 메소드를 활용
        //검색 기능을 담당할 object를 생성
        member _search = new member();
        //검색 결과를 저장할 임시 object를 생성
        member _result = new member();
        _result=_search.searchmember(keyword, _dbmember);
        
        //검색결과를 활용 출력
        System.out.printf("학번 : %s \n", _result.getHakbun());
        System.out.printf("성명 : %s", _result.getName());


    
}
