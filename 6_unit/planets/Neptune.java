package unit6.planets;

import unit6.Earth;

public class Neptune extends Earth {
    @Override
    public float gravityAcceleration() {
        return 11.15f;
    }

    public Neptune() {
        setEquatorialDiameter(13.944f);
        setWeight(3.82f);
        setOrbitalPeriod(1.52f);
        setOrbitalRadius(2.25f);
        setDay(1.40f);
        setSatellite(13);
        setName("Neptune");
    }
}
