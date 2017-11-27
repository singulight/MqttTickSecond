package ru.singulight.mqttsecondtick;

/**
 * Project MqttTickSecond
 * Package ru.singulight.mqttsecondtick
 * Created by Grigorii Nizovoi info@singulight.ru on 04.11.15.
 */
public class SecondTick {


    public static void main(String [] args) {
        System.out.println("yea!!1");
        TickThread tickThread = new TickThread();
        VoltageSend volt = new VoltageSend();
        TemperatureSend temper = new TemperatureSend();
        ThingSend thing = new ThingSend();
        volt.init();
        temper.init();
        tickThread.init();
        thing.init();


        Thread tickth = new Thread(tickThread);
        Thread voltth = new Thread(volt);
        Thread tempth = new Thread(temper);
        Thread thingh = new Thread(thing);

        tickth.start();
        voltth.start();
        tempth.start();
        thingh.start();



        while (true) {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
