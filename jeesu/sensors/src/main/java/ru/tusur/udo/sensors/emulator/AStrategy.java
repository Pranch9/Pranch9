package ru.tusur.udo.sensors.emulator;

public class AStrategy implements EmulationStrategy {

    private int ticks;
    private int counter;
    private int value;
    private int maxScale;
    private int minScale;
    private boolean reverse = false;

    public void setTicks(int ticks) {
        this.ticks = ticks;
        value = 1;
        maxScale = 100;
        minScale = 0;
    }

    public void doEmulate(FakeSensor sensor) {
        counter++;
        if (counter % ticks == 0) {
            sensor.setValue(value);
            if (!reverse) {
                value++;
            } else {
                value--;
            }
            if (value == maxScale) {
                reverse = true;
            } else if (value == minScale) {
                reverse = false;
            }
        }
    }
}

// scope=prototype добавить в аналоговую стратегию
