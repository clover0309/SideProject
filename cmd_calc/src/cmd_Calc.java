import java.util.Scanner;

public class cmd_Calc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("계산기 프로그램입니다.");
        System.out.println("해당 형식대로 숫자와 기호를 입력해주세요!");
        System.out.println("예시 : 2 + 3");
        System.out.println("----------------------------------");
        System.out.println("첫번째 값 입력 : ");
        double a = sc.nextInt();
        System.out.println("기호 입력 (+,-,*,/,%) : ");
        char b = sc.next().charAt(0);
        System.out.println("두번째 값 입력 : ");
        double c = sc.nextInt();

        double result = 0;

        switch(b) {
            case '+':
                result = a + c;
                break;
            case '-':
                result = a - c;
                break;
            case '*':
                result = a * c;
                break;
            case '/':
                result = a / c;
                break;
            case '%':
                result = a % c;
                break;
            default:
                System.out.println("올바른 연산자를 입력해주세요!"+b);
        }
        System.out.println("계산결과 : " + result);
    }
}