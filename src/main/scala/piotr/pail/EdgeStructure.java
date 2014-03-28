package piotr.pail;

import java.util.List;

/**
 * Created by bela on 27.03.14.
 */
public class EdgeStructure implements FieldStructure {
    @Override
    public boolean isValidTarget(String[] dirs) {
        return  true;
    }

    @Override
    public void fillTarget(List<String> ret, Object val) {}
}
