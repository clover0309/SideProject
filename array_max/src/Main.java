public class Main {
    public static void main(String[] args) {
        System.out.println("배열의 최댓값을 찾아봅시다.");

        int[] arr = {20, 50, 60, 80, 100, 300, 1, 3, 44, 77};
        int max = arr[0];
        System.out.println("배열을 길이를 출력해볼께요.");
        System.out.println("배열의 길이는 " + arr.length + " 이네요!");
        System.out.println("배열안에 들어 있는 값은?");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if(i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" 이네요!");
        System.out.println("배열안에 있는 최댓값은?");
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println(max+" 이네요!");
    }
}