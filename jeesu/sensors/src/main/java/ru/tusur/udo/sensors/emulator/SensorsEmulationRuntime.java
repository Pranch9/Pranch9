package ru.tusur.udo.sensors.emulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.tusur.udo.sensors.interfaces.Sensor;
import ru.tusur.udo.sensors.interfaces.SensorRuntime;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SensorsEmulationRuntime extends Thread
        implements SensorRuntime {

    @Value("${runtime.interval}")
    private int runtimeInterval;

    @Autowired
    private Map<String, FakeSensor> fakeSensorMap;

    private static Logger log = LoggerFactory.getLogger(SensorsEmulationRuntime.class);

    public List<Sensor> getSensors() {
        return fakeSensorMap
                .values()
                .stream()
                .map(fakeSensor -> fakeSensor.toPureSensor())
                .collect(Collectors.toList());
        // map и filter, создает новую коллекцию. For each жтого не делает.
    }

// stream() в контексте коллекции. Есть базовые операции #for each

    public void run() {
        while (true) {
            emulate();
            try {
                sleep(runtimeInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void emulate() {

        // лямбда выражения, разобрать!!!
        fakeSensorMap.forEach((str, value) -> {
            value.emulate();
            //log.info("Sensor: name=" + value.getName() + " value= " + value.getValue());
        });
    }
}
