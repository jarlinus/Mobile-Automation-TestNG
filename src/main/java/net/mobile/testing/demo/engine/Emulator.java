package net.mobile.testing.demo.engine;

import net.mobile.testing.demo.Configuration;

import java.io.IOException;

public class Emulator {

    private static final Emulator instance = new Emulator();
    private final Configuration configuration = Configuration.getInstance();
    private final String deviceName = configuration.getProperty("device.name");

    public static Emulator getInstance() {
        return instance;
    }

    public void startEmulator() {
        if (deviceName.contains("emulator")) {
            try {
                Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\startEmulator.bat");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
