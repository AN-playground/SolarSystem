package solar.astronomicalbodies;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public abstract class AstronomicalBody {
    String name;
    long distanceToOrbitingObject;
}
