package ru.singulight.mqttsecondtick;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by Grigorii Nizovoi info@singulight.ru on 25.03.16.
 */
public class ThingSend implements Runnable {
    private MqttClient mClient = null;

    public void init() {
        try {
            mClient = new MqttClient("tcp://192.168.1.69","Thing");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (true) {
            String JSONString = "{\"ver\":\"1.0\",\"id\":\"00000000000000f5\",\"name\":\"RGB светильник\",\"location\":\"\",\"actuators\":[{\"topic\":\"duffelbag/rgb/00000000000000f6\",\"name\":\"RGB лампа\",\"minvalue\":\"0.0\",\"maxvalue\":\"1.0\"}],\"sensors\":[{\"topic\":\"duffelbag/voltage/00000000000000f7\",\"name\":\"Входное напряжение\",\"minvalue\":\"-8\",\"maxvalue\":\"8\"},{\"topic\":\"duffelbag/power/00000000000000f8\",\"name\":\"Потребляемая мощность\",\"minvalue\":\"0\",\"maxvalue\":\"30\"}]}";
            MqttMessage mqttMessage = new MqttMessage(JSONString.getBytes());
            try {
                mClient.connect();
                mClient.publish("duffelbag/outlet/00000000000000f5", mqttMessage);
                mClient.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
