package solar.astronomicalbodies;

import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
public class Moon extends AstronomicalBody {
    String orbitedPlanet;

    @Override
    public String toString() {
        return "Moon{" +
                "name='" + name + '\'' +
                ", distanceToOrbitingObject=" + distanceToOrbitingObject +
                ", orbitedPlanet='" + orbitedPlanet + '\'' +
                '}';
    }
}
