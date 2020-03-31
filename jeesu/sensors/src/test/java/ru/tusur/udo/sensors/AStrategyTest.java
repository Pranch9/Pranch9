package ru.tusur.udo.sensors;

import org.junit.Test;
import ru.tusur.udo.sensors.emulator.AStrategy;
import ru.tusur.udo.sensors.emulator.EmulationStrategy;
import ru.tusur.udo.sensors.emulator.FakeSensor;

import static org.junit.Assert.assertEquals;

public class AStrategyTest {

    @Test
    public void testAStrategy() {

        int TEST_AMOUNT = 250;
        int testingValue = 0;
        int maxScale = 100;
        int minScale = 0;
        int ticks = 10;
        boolean reverse = false;

        FakeSensor sensor = new FakeSensor();
        EmulationStrategy strategy = new AStrategy();
        strategy.setTicks(ticks);
        sensor.setEmulationStrategy(strategy);

        for (int i = 0; i < TEST_AMOUNT; i++) {
            sensor.emulate();
            if ((i != 0) && (i % ticks == 0)) {
                if (!reverse) {
                    testingValue++;
                } else testingValue--;
                if (testingValue == maxScale) {
                    reverse = true;
                } else if (testingValue == minScale) {
                    reverse = false;
                }
                assertEquals(sensor.getValue(), testingValue);
                System.out.print(testingValue+" ");
            }
        }
    }
}
