package net.mobile.testing.demo.engine;

import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerStart {

    private static final ServerStart instance = new ServerStart();
    public static AppiumDriverLocalService service;

    public static ServerStart getInstance() {
        return instance;
    }
    public AppiumDriverLocalService startServer() {
        boolean flag = checkIfServerIsUp(4723);
        if (!flag) {
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
        return service;
    }

    public static boolean checkIfServerIsUp(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }
}
