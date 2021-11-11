package solar.enums;

import static solar.enums.PlanetDistances.*;

public enum MoonDistances {
    //Distance is presented in kilometers as a semi-major axis
    MOON(384399, EARTH.name()),
    PHOBOS(9380, MARS.name()),
    DEIMOS(23460, MARS.name()),
    IO(421800, JUPITER.name()),
    EUROPA(671100, JUPITER.name()),
    GANYMEDE(1070400, JUPITER.name()),
    CALLISTO(1882700, JUPITER.name()),
    MIMAS(185540, SATURN.name()),
    ENCELADUS(238040, SATURN.name()),
    TETHYS(294670, SATURN.name()),
    DIONE(377420, SATURN.name()),
    RHEA(527070, SATURN.name()),
    TITAN(1221870, SATURN.name()),
    LAPETUS(3560840, SATURN.name()),
    ARIEL(190900, URANUS.name()),
    UMBRIEL(266000, URANUS.name()),
    TITANIA(436300, URANUS.name()),
    OBERON(583500, URANUS.name()),
    MIRANDA(129900, URANUS.name()),
    TRITON(354800, NEPTUNE.name());


    public final long distanceToSun;
    public final String orbitedPlanet;

    MoonDistances(long distanceToSun, String orbitedPlanet) {
        this.distanceToSun = distanceToSun;
        this.orbitedPlanet = orbitedPlanet;
    }

    public static MoonDistances fromString(String name) {
        for (MoonDistances b : MoonDistances.values()) {
            if (b.name().equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
    }
}
