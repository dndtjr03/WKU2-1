package ex_member;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        //3���� �ν��Ͻ�(object)�� ����
        //3���� ���� ������Ʈ�� �����ϱ� ���� ���� ��ü�� ������ ��
        //�迭�� �̿���
        //�迭�� ���� ���: �ڷ���[] ������;
        //�迭�� ����-���� ���� : �ڷ���[] ������ = new �ڷ��[ũ��];
        //�ڷ��� --> ��� ������ member�� ������ ���� ������ �Ѵ�.
        //�ڷ��� --> member
        member[] _dbmember = new member[3];
        //�� �ν��Ͻ� ����
        _dbmember[0] = new member("1111", "ȫ�浿", "21",)
        _dbmember[1] = new member();
        _dbmember[2] = new member();
        //����ڰ� �˻��� �� �ֵ��� �������̽��� ����
        System.out.printf("�˻��� �Է�(�й�): ");
        Scanner sc = new Scanner(System.in); //��ĳ�� ���
        String keyword = sc.nextLine(); //�Է� ó��
        //�˻� ����� ó���ϰ�, �ش� ����� ���
        //�˻� ����� membeer Ŭ������ ���ǵ� ��ü �˻� �޼ҵ带 Ȱ��
        //�˻� ����� ����� object�� ����
        member _search = new member();
        //�˻� ����� ������ �ӽ� object�� ����
        member _result = new member();
        _result=_search.searchmember(keyword, _dbmember);
        
        //�˻������ Ȱ�� ���
        System.out.printf("�й� : %s \n", _result.getHakbun());
        System.out.printf("���� : %s", _result.getName());


    
}
