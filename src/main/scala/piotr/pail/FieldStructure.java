package piotr.pail;

import java.util.List;

/**
 * Created by bela on 27.03.14.
 */
public interface FieldStructure {
    public boolean isValidTarget(String[] dirs);
    public void fillTarget(List<String> ret, Object val);
}