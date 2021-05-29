package cn.shenxw.transport;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * create by shenxiangwei on 2021/5/15 下午 10:15
 */
@Slf4j
public class RpcSocketServer {

    public static final int PORT = 9898;

    public void start(){
        try (ServerSocket serverSocket = new ServerSocket();){
            String host = InetAddress.getLocalHost().getHostAddress();
            serverSocket.bind(new InetSocketAddress(host,PORT));
            Socket socket = serverSocket.accept();
            log.info("客戶端{}已连接",socket.getInetAddress());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("收到客户端消息："+bufferedReader.readLine());

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("hello,client\n");
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws IOException {

        String host = InetAddress.getLocalHost().getHostAddress();
        System.out.println(host);
        Socket clint = new Socket(host,9898);

        OutputStream outputStream = clint.getOutputStream();

        outputStream.write("hello,server\n".getBytes());
        outputStream.flush();

        InputStream inputStream = clint.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());

    }

}
