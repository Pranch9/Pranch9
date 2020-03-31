package ru.tusur.udo.sensors;

import ru.tusur.udo.sensors.interfaces.Sensor;

import java.util.Date;
import java.util.List;

public interface SensorsJsonSchema {

    String getNode();

    default long getTimeStamp() {
        return new Date().getTime();
    }

    default List<Sensor> getSensors() {
        return null;
    }
}
