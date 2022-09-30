package run;

import messages.CommandMessage;
import messages.CommandResponse;
import util.ClientOutputBuilder;
import util.CollectionManager;
import util.Console;
import util.RequestHandler;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Server {
    private int port;
    private int soTimeout;
    private DatagramSocket ds;
    private DatagramPacket dp;
    private InetAddress host;
    private RequestHandler handler;
    private SocketAddress address;
    private DatagramChannel channel;
    private ByteBuffer buffer;
    private final int standartLength = 16384;
    private CollectionManager colmanager;


    public Server(int port, int time, RequestHandler handler, CollectionManager colmanager){
        this.port = port;
        soTimeout = time;
        this.handler = handler;
        this.colmanager = colmanager;
    }

    public void start(){
        try{
            ds = new DatagramSocket(port);
            channel = DatagramChannel.open();
            address = new InetSocketAddress(port + 1);
            channel.bind(address);
            boolean isProcessing = true;
            CommandMessage request;
            CommandResponse response;
            while (isProcessing){
                byte[] bytes = new byte[standartLength];
                dp = new DatagramPacket(bytes, bytes.length);
                ds.receive(dp);
                ByteArrayInputStream byteInput = new ByteArrayInputStream(bytes);
                ObjectInputStream objectInput = new ObjectInputStream(byteInput);
                request = (CommandMessage) objectInput.readObject();

                host = dp.getAddress();
                port = dp.getPort();
                Console.print("Пришел запрос от " + host + ":" + port + "\n" + request.toString() + "\n");
                byteInput.close();
                objectInput.close();
                response = handler.handle(request);
                ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
                ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
                objectOutput.writeObject(response);
                objectOutput.flush();
                byte arr[];
                arr = byteOutput.toByteArray();
                byteOutput.flush();
                buffer = ByteBuffer.wrap(arr);
                SocketAddress addr = new InetSocketAddress(host, port + 1);
                channel.send(buffer, addr);
                Console.print("Отправлен ответ на запрос от " + host + ":" + port + "\n" + response.toString() + "\n");
                //dp = new DatagramPacket(arr, arr.length, host, port);
                //ds.send(dp);
                if (request.getCommandName().equals("exit_server") && request.getCommandArgument().isEmpty() && request.getObjectArgument() == null){
                    colmanager.saveCollection();
                    ClientOutputBuilder.refresh();
                    System.exit(0);
                }

            }
        }
        catch (SocketException e){
            ClientOutputBuilder.printerr("Не удалось установить соединение!");
        }
        catch (IOException e){
            e.printStackTrace();
            ClientOutputBuilder.printerr("Ошибка ввода-вывода!");
        } catch (ClassNotFoundException e) {
            ClientOutputBuilder.printerr("Класс не найден!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
