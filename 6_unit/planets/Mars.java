package unit6.planets;

import unit6.Earth;

public class Mars extends Earth {
    @Override
    public float gravityAcceleration() {
        return 3.721f;
    }

    public Mars() {
        setEquatorialDiameter(0.53f);
        setWeight(0.11f);
        setOrbitalPeriod(1.52f);
        setOrbitalRadius(1.88f);
        setDay(1.03f);
        setSatellite(2);
        setName("Mars");
    }
}
