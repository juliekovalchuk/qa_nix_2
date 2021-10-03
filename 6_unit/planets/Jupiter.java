package unit6.planets;

import unit6.Earth;

public class Jupiter extends Earth {
    @Override
    public float gravityAcceleration() {
        return 24.79f;
    }

    public Jupiter() {
        setEquatorialDiameter(11.2f);
        setWeight(318.0f);
        setOrbitalPeriod(5.52f);
        setOrbitalRadius(11.88f);
        setDay(0.413f);
        setSatellite(69);
        setName("Jupiter");
    }
}
