import netParts.Server;
import netParts.SockRW;

public class ServerCheck {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SockRW sockRW = new SockRW();
        Server serv = new Server(8081, sockRW, sockRW);
        //Server serv1 = new Server(8082);
        serv.run();
    }


}
