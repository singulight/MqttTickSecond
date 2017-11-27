package ru.singulight.mqttsecondtick;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Project MqttTickSecond
 * Package ru.singulight.mqttsecondtick
 * Created by Grigorii Nizovoi info@singulight.ru on 06.11.15.
 */
public class TickThread implements Runnable {

    private MqttClient mClient = null;

    public void init() {
        try {
            mClient = new MqttClient("tcp://192.168.1.69","TestSecondTickk");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        Integer i = 0;
        while (true) {
            MqttMessage mqttMessage = new MqttMessage(("test second tick "+i.toString()).getBytes());
            try {
                mClient.connect();
                mClient.publish("duffelbag/text/00000000000000f2", mqttMessage);
                mClient.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
