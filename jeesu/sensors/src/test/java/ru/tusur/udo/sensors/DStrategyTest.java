package ru.tusur.udo.sensors;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.tusur.udo.sensors.emulator.DStrategy;
import ru.tusur.udo.sensors.emulator.EmulationStrategy;
import ru.tusur.udo.sensors.emulator.FakeSensor;

public class DStrategyTest {

    @Test
    public void testDStrategy() {

        int TEST_AMOUNT = 6000;
        int testingValue = 0;
        int ticks = 3;

        FakeSensor sensor = new FakeSensor();
        EmulationStrategy strategy = new DStrategy();
        strategy.setTicks(ticks);
        sensor.setEmulationStrategy(strategy);

        for (int i = 0; i < TEST_AMOUNT; i++) {
            sensor.emulate();
            if ((i != 0) && (i % ticks == 0)) {
                testingValue = testingValue > 0 ? 0 : 1;
            }
            assertEquals(sensor.getValue(), testingValue);
        }

        testingValue = 0;
        ticks = 17;
        strategy.setTicks(ticks);
        for (int i = 0; i < TEST_AMOUNT; i++) {
            sensor.emulate();
            if ((i != 0) && (i % ticks == 0)) {
                testingValue = testingValue > 0 ? 0 : 1;
            }
            assertEquals(sensor.getValue(), testingValue);
        }
    }
}