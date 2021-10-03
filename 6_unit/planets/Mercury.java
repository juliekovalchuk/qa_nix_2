package unit6.planets;

import unit6.Earth;

public class Mercury extends Earth {

    @Override
    public float gravityAcceleration() {
        return 3.7f;
    }

    public Mercury() {
        setDay(58.6f);
        setName("Mercury");
        setEquatorialDiameter(0.382f);
        setWeight(0.06f);
        setOrbitalPeriod(0.241f);
        setOrbitalRadius(0.38f);
        setSatellite(0);
    }
}
