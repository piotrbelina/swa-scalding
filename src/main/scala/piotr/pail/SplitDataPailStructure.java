package piotr.pail;

import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import piotr.swa.Data;
import piotr.swa.DataUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bela on 27.03.14.
 */
public class SplitDataPailStructure extends DataPailStructure {
    public static HashMap<Short, FieldStructure> validFieldMap = new HashMap<Short, FieldStructure>();

    static {
        for (DataUnit._Fields k : DataUnit.metaDataMap.keySet()) {
            FieldValueMetaData md = DataUnit.metaDataMap.get(k).valueMetaData;
            FieldStructure fieldStruct;

            if (md instanceof StructMetaData && ((StructMetaData) md).structClass.getName().endsWith("Property")) {
                fieldStruct = new PropertyStructure(((StructMetaData) md).structClass);
            } else {
                fieldStruct = new EdgeStructure();
            }

            validFieldMap.put(k.getThriftFieldId(), fieldStruct);
        }
    }
    public List<String> getTarget(Data object) {
        List<String> ret = new ArrayList<String>();
        DataUnit du = ((Data)object).getDataunit();
        short id = du.getSetField().getThriftFieldId();
        ret.add("" + id);
        validFieldMap.get(id).fillTarget(ret, du.getFieldValue());
        return ret;
    }

    public boolean isValidTarget(String[] dirs) {
        if (dirs.length == 0) return false;

        try {
            short id = Short.parseShort(dirs[0]);
            FieldStructure s = validFieldMap.get(id);
            if (s==null) return false;
            else return s.isValidTarget(dirs);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

