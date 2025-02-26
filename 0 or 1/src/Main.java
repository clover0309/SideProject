import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("홀짝을 구분을 해봅시다.");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        if(a % 2 == 1){
            System.out.println("홀수입니다!");
        }else {
            System.out.println("짝수입니다!");
        }
        if(b % 2 != 1){
            System.out.println("짝수입니다!");
        }else {
            System.out.println("홀수입니다!");
        }
    }
}