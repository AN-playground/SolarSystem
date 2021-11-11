package solar.services;

import org.junit.jupiter.api.Test;
import solar.astronomicalbodies.AstronomicalBody;
import solar.astronomicalbodies.Planet;
import solar.enums.MoonDistances;
import solar.enums.PlanetDistances;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class SolarServiceTest {


    @Test
    void shouldInitializePlanetsCorrectly() {
        //given
        Map<String, AstronomicalBody> expected = new HashMap<>();
        Arrays.stream(PlanetDistances.values()).forEach(value -> createExpectedPlanet(value, expected));

        SolarService solarService = new SolarService();

        //when
        Map<String, AstronomicalBody> actual = solarService.initializePlanets();

        //then
        expected.keySet().forEach(name -> assertIfPresent(actual, expected, name));

    }

    @Test
    void shouldInitializeMoonsCorrectly() {
        //given
        Map<String, AstronomicalBody> expected = new HashMap<>();
        Arrays.stream(MoonDistances.values()).forEach(value -> createExpectedMoons(value, expected));

        SolarService solarService = new SolarService();

        //when
        Map<String, AstronomicalBody> actual = solarService.initializeMoons();

        //then
        expected.keySet().forEach(name -> assertIfPresent(actual, expected, name));
    }

    private void createExpectedPlanet(PlanetDistances value, Map<String, AstronomicalBody> expected) {
        expected.put(value.name(), Planet.builder().name(value.name()).distanceToOrbitingObject(value.distanceToSun).build());
    }

    private void createExpectedMoons(MoonDistances value, Map<String, AstronomicalBody> expected) {
        expected.put(value.name(), Planet.builder().name(value.name()).distanceToOrbitingObject(value.distanceToSun).build());
    }

    private void assertIfPresent(Map<String, AstronomicalBody> actual, Map<String, AstronomicalBody> expected, String name) {
        AstronomicalBody actualBody = actual.get(name);
        AstronomicalBody expectedBody = expected.get(name);

        assertThat(actualBody.getName(), is(expectedBody.getName()));
        assertThat(actualBody.getDistanceToOrbitingObject(), is(expectedBody.getDistanceToOrbitingObject()));
    }
}