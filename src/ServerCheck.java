import NetParts.Server;

public class ServerCheck {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Server serv = new Server(8081);
        //Server serv1 = new Server(8082);
        while(true){
            var recv = serv.readAll();
            for(String str:recv){
                System.out.println(str);
            }
        }
    }


}
