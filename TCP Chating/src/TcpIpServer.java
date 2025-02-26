import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TcpIpServer {
    public static void main(String[] args) {
        //ServerSocket의 serverSocket을 null값으로 지정.
        ServerSocket serverSocket = null;
        //Scoket의 socket을 null값으로 지정.
        Socket socket = null;

        //예외처리
        //포트가 사용중이거나 네트워크 바인딩 실패상황 및 서버의 비정상 종료를
        //방지하고 시스템이 계속 실행되기 위해서 예외처리를 함.
        try {
            //서버소켓을 생성, 7777번 포트와 결합.
            serverSocket = new ServerSocket(7777);
            System.out.println("Server AlReady.");

            //socket의 연결요청을 대기.
            socket = serverSocket.accept();
            System.out.println("[서버의 채팅창]");

            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);

            //sender와 receiver 스레드 시작.
            sender.start();
            receiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //main 영역 끝.
} //class 영역 끝.

//새로운 클래스 Sender에 Thread를 상속한다.
    class Sender extends Thread {
    //Socket을 socket으로 선언.
    Socket socket;
    //DataOutputStream을 out으로 선언.
    DataOutputStream out;
    //문자열 name을 선언.
    String name;

    //Sender는 Socket의 socket을 인자값으로 받는다.
        Sender(Socket socket) {
            //이영역의 소켓을 소켓으로 지정.
                this.socket  = socket;
                //예외처리
                /*네트워크의 연결이 끊기거나, 스트림 생성에 실패할 경우를 위해 예외처리. */
                try {
                    //out을 DataOutputStream으로 생성하는데, socket의 아웃풋 스트림을 얻어온다.
                    out = new DataOutputStream(socket.getOutputStream());
                    //name은 ip주소와 포트를 얻어와 name에 저장한다.
                    name = "[" + socket.getInetAddress() + ":" + socket.getPort() + "]";
                } catch (Exception e) {}
            }

            //run 메서드 생성. 반환하지않음.
            public void run() {
                Scanner scan = new Scanner(System.in);

                //반복문 시작. out이 null이 아닌경우에만 반복
                while(out!=null) {
                    //예외처리
                    try {
                        out.writeUTF(name+scan.nextLine());
                    } catch (IOException e) {}
                }
            } // 여기까지가 run 메서드 영역.
}


//Receiver 클래스를 생성, Thread를 상속받음.
class Receiver extends Thread {
    //Socket을 socket으로 선언.
    Socket socket;
    //DataInputStream을 in으로 선언.
    DataInputStream in;

    //Socket 객체를 매개변수로 받는 Receiver 생성자 생성.
    Receiver(Socket socket) {
        //이영역의 socket을 socket으로 선언.
        this.socket = socket;
        //예외처리
        try {
            //in을 DataInputStream을 socket의 InputStream을 인자값으로 받아옴.
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //반환하지 않는 run 메서드 생성.
    public void run() {
        //반복문 시작 in이 null이 아닐경우.
        while(in != null) {
            //예외처리
            /*데이터를 읽고 쓰는 과정에서 네트워크 오류가 발생해도
            서버가 계속 구동되기 위해서 예외처리를 진행. */
            try {
                System.out.println(in.readUTF());
            } catch (IOException e) {}
        }
    }// run 메서드 끝.
}


