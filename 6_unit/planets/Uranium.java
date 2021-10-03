package unit6.planets;

import unit6.Earth;

public class Uranium extends Earth {
    @Override
    public float gravityAcceleration() {
        return 8.87f;
    }

    public Uranium() {
        setEquatorialDiameter(24.949f);
        setWeight(1.82f);
        setOrbitalPeriod(2.72f);
        setOrbitalRadius(0.215f);
        setDay(0.420f);
        setSatellite(27);
        setName("Uranium");
    }
}
