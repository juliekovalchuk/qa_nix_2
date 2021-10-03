package unit6;

import unit6.planets.Jupiter;
import unit6.planets.Mars;
import unit6.planets.Neptune;
import unit6.planets.Saturn;
import unit6.planets.Uranium;
import unit6.planets.Venus;

public class PlanetReporter {
    public static void printInfoAboutPlanet(Earth abstractPlanet) {
        System.out.println(abstractPlanet.toString());
    }

    public static void main(String[] args) {
        printInfoAboutPlanet(new Uranium());
        printInfoAboutPlanet(new unit6.planets.Earth());
        printInfoAboutPlanet(new Jupiter());
        printInfoAboutPlanet(new Mars());
        printInfoAboutPlanet(new Neptune());
        printInfoAboutPlanet(new Saturn());
        printInfoAboutPlanet(new Venus());
    }
}
