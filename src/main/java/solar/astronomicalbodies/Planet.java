package solar.astronomicalbodies;

import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
public class Planet extends AstronomicalBody {

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", distanceToOrbitingObject=" + distanceToOrbitingObject +
                '}';
    }
}
