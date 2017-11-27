package ru.singulight.mqttsecondtick;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Grigorii Nizovoi info@singulight.ru on 12.01.16.
 */
public class VoltageSend implements Runnable {

    private MqttClient mClient = null;

    public void init() {
        try {
            mClient = new MqttClient("tcp://192.168.1.69","TestVoltage");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        double i = 0.0;
        double r;
        while (true) {
            r = (Math.sin(i))*8;
            MqttMessage mqttMessage = new MqttMessage(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat((float) r).array());
            try {
                mClient.connect();
                mClient.publish("duffelbag/voltage/00000000000000f7", mqttMessage);
                mClient.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
            i += 0.1f;
            try {
                Thread.sleep(1610);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
