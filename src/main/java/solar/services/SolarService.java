package solar.services;

import solar.astronomicalbodies.AstronomicalBody;
import solar.astronomicalbodies.Moon;
import solar.astronomicalbodies.Planet;
import solar.enums.MoonDistances;
import solar.enums.PlanetDistances;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class SolarService {

    //in this case I accept some duplication to avoid complexity
    public Map<String, AstronomicalBody> initializePlanets() {
        Map<String, AstronomicalBody> planets = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        Arrays.stream(PlanetDistances.values())
                .map(this::createPlanet)
                .forEach(planet -> planets.put(planet.getName(), planet));
        return planets;
    }

    public Map<String, AstronomicalBody> initializeMoons() {
        Map<String, AstronomicalBody> moons = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        Arrays.stream(MoonDistances.values())
                .map(this::createMoon)
                .forEach(moon -> moons.put(moon.getName(), moon));
        return moons;
    }

    private Planet createPlanet(PlanetDistances planet) {
        return Planet.builder()
                .name(planet.name())
                .distanceToOrbitingObject(planet.distanceToSun)
                .build();
    }

    private Moon createMoon(MoonDistances moon) {
        return Moon.builder()
                .name(moon.name())
                .distanceToOrbitingObject(moon.distanceToSun)
                .orbitedPlanet(moon.orbitedPlanet)
                .build();
    }

    public long calculateDistance(long from, long to) {
        return Math.abs(from - to);
    }
}
