package solar;

import lombok.extern.slf4j.Slf4j;
import solar.astronomicalbodies.AstronomicalBody;
import solar.enums.MoonDistances;
import solar.services.SolarService;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

@Slf4j
public class SolarSystemApplication {

    static SolarService solarService = new SolarService();
    static Map<String, AstronomicalBody> planets = solarService.initializePlanets();
    static Map<String, AstronomicalBody> moons = solarService.initializeMoons();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        log.info("Here is the list of available Planets");
        planets.entrySet().forEach(planet -> log.info(planet.toString()));

        log.info("Here is the list of available Moons");
        moons.entrySet().forEach(moon -> log.info(moon.toString()));

        do {
            log.info("Enter the name of a Planet/Moon that you would like to measure distance from ");
            String fromPoint = scanner.next();

            fromPoint = validate(fromPoint, scanner);

            log.info("Enter the name of a Planet/Moon that you would like to measure distance to ");
            String toPoint = scanner.next();

            toPoint = validate(toPoint, scanner);

            long distance = 0;

            if (isPlanet(fromPoint) && isPlanet(toPoint)) {
                distance = solarService.calculateDistance(planets.get(fromPoint).getDistanceToOrbitingObject(), planets.get(toPoint).getDistanceToOrbitingObject());
            } else if (isMoon(fromPoint) && isPlanet(toPoint)) {
                long totalDistanceToTheMoonFromSun = getTotalDistanceToTheMoonFromSun(fromPoint);
                distance = solarService.calculateDistance(totalDistanceToTheMoonFromSun, planets.get(toPoint).getDistanceToOrbitingObject());
            } else if (isPlanet(fromPoint) && isMoon(toPoint)) {
                long totalDistanceToTheMoonFromSun = getTotalDistanceToTheMoonFromSun(toPoint);
                distance = solarService.calculateDistance(planets.get(fromPoint).getDistanceToOrbitingObject(), totalDistanceToTheMoonFromSun);
            } else if (isMoon(fromPoint) && isMoon(toPoint)) {
                long firstTotalDistanceToTheMoonFromSun = getTotalDistanceToTheMoonFromSun(fromPoint);
                long secondTotalDistanceToTheMoonFromSun = getTotalDistanceToTheMoonFromSun(toPoint);
                distance = solarService.calculateDistance(firstTotalDistanceToTheMoonFromSun, secondTotalDistanceToTheMoonFromSun);
            } else {
                log.warn("No planet or moon with such name exists");
            }

            log.info("Distance between {} and {} is : {}",fromPoint, toPoint, distance);
            log.info("Would you like to continue ? Y/N ");
        } while (!scanner.next().equalsIgnoreCase("N"));
    }

    private static String validate(String astronomicalBodyName, Scanner scanner) {
        do {
            if (!isPlanet(astronomicalBodyName) && !isMoon(astronomicalBodyName)) {
                log.error("The value you entered is neither a Planet or a Moon, please try again");
                astronomicalBodyName = scanner.next();
            }
        } while (!isPlanet(astronomicalBodyName) && !isMoon(astronomicalBodyName));
        return astronomicalBodyName;
    }

    private static long getTotalDistanceToTheMoonFromSun(String moon) {
        long distanceToOrbitingObjectOfTheMoon = Objects.requireNonNull(MoonDistances.fromString(moon)).distanceToSun;
        long distanceToSunFromThePlanetOfTheMoon = planets.get(Objects.requireNonNull(MoonDistances.fromString(moon)).orbitedPlanet).getDistanceToOrbitingObject();
        return distanceToOrbitingObjectOfTheMoon + distanceToSunFromThePlanetOfTheMoon;
    }

    static boolean isPlanet(String astronomicalBody) {
        return planets.keySet().stream().anyMatch(planet -> planet.equalsIgnoreCase(astronomicalBody));
    }

    static boolean isMoon(String astronomicalBody) {
        return moons.keySet().stream().anyMatch(moon -> moon.equalsIgnoreCase(astronomicalBody));
    }

}
