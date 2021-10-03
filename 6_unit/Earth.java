package unit6;

public abstract class Earth implements Planet {
    private String name;
    private float equatorialDiameter; // Earth diameters
    private float weight; //Earth masses
    private float orbitalRadius; // measured in astronomical unit
    private float orbitalPeriod;
    private float day; // Earth day
    private int satellite;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getEquatorialDiameter() {
        return equatorialDiameter;
    }

    public void setEquatorialDiameter(float equatorialDiameter) {
        this.equatorialDiameter = equatorialDiameter;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getOrbitalRadius() {
        return orbitalRadius;
    }

    public void setOrbitalRadius(float orbitalRadius) {
        this.orbitalRadius = orbitalRadius;
    }

    public float getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(float orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public float getDay() {
        return day;
    }

    public void setDay(float day) {
        this.day = day;
    }

    public int getSatellite() {
        return satellite;
    }

    public void setSatellite(int satellite) {
        this.satellite = satellite;
    }

    @Override
    public String toString() {
        return "Earth{" +
                "name='" + getName() + '\'' +
                ", equatorialDiameter=" + getEquatorialDiameter() +
                ", weight=" + getWeight() +
                ", orbitalRadius=" + getOrbitalRadius() +
                ", orbitalPeriod=" + getOrbitalPeriod() +
                ", day=" + getDay() +
                ", gravity acceleration=" + gravityAcceleration() +
                ", satellite=" + getSatellite() +
                '}';
    }
}
