package unit6.planets;

import unit6.Earth;

public class Saturn extends Earth {
    @Override
    public float gravityAcceleration() {
        return 10.44f;
    }

    public Saturn() {
        setEquatorialDiameter(9.949f);
        setWeight(95.0f);
        setOrbitalPeriod(9.72f);
        setOrbitalRadius(7.615f);
        setDay(0.457f);
        setSatellite(62);
        setName("Saturn");
    }
}
