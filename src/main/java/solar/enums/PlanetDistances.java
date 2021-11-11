package solar.enums;

public enum PlanetDistances {
    //Distance is presented in kilometers
    MERCURY(57900000),
    VENUS(108200000),
    EARTH(149600000),
    MARS(227900000),
    JUPITER(778600000),
    SATURN(1433500000),
    URANUS(2872500000L),
    NEPTUNE(4495100000L);

    public final long distanceToSun;

    PlanetDistances(long distanceToSun) {
        this.distanceToSun = distanceToSun;
    }
}
