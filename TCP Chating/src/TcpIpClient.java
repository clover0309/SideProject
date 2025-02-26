import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class TcpIpClient {
    public static void main(String[] args) {
        /*예외처리. 아마도 ip가 달라 소켓을 생성하지 못했을 시를 위한
        예외처리로 예상됨*/
        try{
            //정수형 serverIp에 아이피를 넣어줌.
            String serverIp = "127.0.0.1";
            //Socket형 socket을 생성해 ip와 포트에 연결요청
            Socket socket = new Socket(serverIp, 7777);

            System.out.println("Connected");
            System.out.println("[Client's Chating Field]");

            //Sender형의 sender를 socket의 인자값을 가진 것으로 새로생성.
            Sender sender = new Sender(socket);
            //Receiver형의 receiver를 socket의 인자값을 가진 것으로 새로생성.
            Receiver receiver = new Receiver(socket);

            //sender, receiver 스레드 시작.
            sender.start();
            receiver.start();
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //main 끝
} // class 끝.
