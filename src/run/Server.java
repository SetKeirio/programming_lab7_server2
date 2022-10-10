package run;

import exceptions.ConnectionTroublesException;
import util.CommandManager;
import util.Console;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Server {
    private int port;
    private InetAddress host;
    //private DatagramSocket ds;
    private DatagramPacket dp;
    //private SocketAddress address;
    //private DatagramChannel channel;
    //private ByteBuffer buffer;
    private final int standartLength = 16384;
    private CommandManager commanager;
    private Semaphore semaphore;
    private DatagramSocket ds;
    private boolean isWorking;
    private ExecutorService pool = Executors.newCachedThreadPool();


    public Server(int port, CommandManager commanager){
        this.port = port;
        this.commanager = commanager;
        semaphore = new Semaphore(50);
    }

    private synchronized boolean isWorking(){
        return isWorking;
    }

    public synchronized void stop(){
        isWorking = false;
        pool.shutdown();
        Console.println("Сервер завершает работу с клиентами!");
    }

    public void allow(){
        try{
            semaphore.acquire();
            Console.println("Соединение разрешено!");
        } catch (InterruptedException e) {
            Console.printerr("Ошибка при получении разрешения!");
        }
    }

    public void release(){
        semaphore.release();
        Console.println("Соединение разорвано!");
    }

    private void openServer() throws SocketException{
        try{
            ds = new DatagramSocket(port);
            ds.setSoTimeout(10000);
            isWorking = true;
            Console.println("Сервер запущен!");
        } catch (SocketException e) {
            Console.printerr("Сервер не может быть запущен!");
            throw new SocketException();
        }
    }

    private void recieve(){
        try{
            Console.println("Слушаю порт: " + port);
            byte[] bytes = new byte[standartLength];
            dp = new DatagramPacket(bytes, bytes.length);
            ds.receive(dp);
        } catch (IOException e) {
            Console.printerr("Произошла ошибка во время получения датаграммы!");
        }
    }

    public void start(){
        try{
            openServer();
            while (isWorking){
                try{
                    allow();
                    if (!isWorking){
                        throw new ConnectionTroublesException();
                    }
                    recieve();
                    //pool.submit(new );
                } catch (ConnectionTroublesException e) {
                    if (isWorking){
                        Console.printerr("Невозможно соединиться с клиентом!");
                    }
                    else{
                        break;
                    }
                }
            }
            pool.awaitTermination(30000, TimeUnit.MILLISECONDS);
            Console.println("Сервер завершает свою работу!");
        } catch (SocketException | InterruptedException e) {
            Console.printerr("Произошла ошибка при завершении работы!");
        }

    }

    /**public void start(){
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
    }**/
}
