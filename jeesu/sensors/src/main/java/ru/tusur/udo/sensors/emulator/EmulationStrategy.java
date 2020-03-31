package ru.tusur.udo.sensors.emulator;

public interface EmulationStrategy {

    void doEmulate(FakeSensor sensor);

    void setTicks(int ticks);

}
