package unit6.planets;

import unit6.Earth;

public class Venus extends Earth {
    @Override
    public float gravityAcceleration() {
        return 8.87f;
    }

    public Venus() {
        setEquatorialDiameter(0.949f);
        setWeight(0.82f);
        setOrbitalPeriod(0.72f);
        setOrbitalRadius(0.615f);
        setDay(-243.0f);
        setSatellite(0);
        setName("Venus");
    }
}
