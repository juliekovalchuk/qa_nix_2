package unit6.planets;

public class Earth extends unit6.Earth {
    @Override
    public float gravityAcceleration() {
        return 9.807f;
    }

    public Earth() {
        setEquatorialDiameter(1.0f);
        setWeight(1.0f);
        setOrbitalPeriod(1.0f);
        setOrbitalRadius(1.0f);
        setDay(-1.0f);
        setSatellite(1);
        setName("Earth");
    }
}
