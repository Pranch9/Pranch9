package ru.tusur.udo.sensors.emulator;

public class DStrategy implements EmulationStrategy {
    private int counter;
    private int value;
    private int ticks;

    public void setTicks(int ticks) {
        counter = 0;
        value = 0;
        this.ticks = ticks;
    }

    public void doEmulate(FakeSensor sensor) {

        counter++;
        if (counter <= ticks) {
            sensor.setValue(value);
        } else {
            value = 1;
            sensor.setValue(value);
            if (counter == ticks * 2) {
                counter = 0;
                value = 0;
            }
        }
    }
}