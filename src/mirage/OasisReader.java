package mirage;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OasisReader extends data.FileReader {
    public OasisReader(String filePath) {
        super(filePath);
    }

    public ArrayList<Long> findReadings(String s) {
        ArrayList<Long> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            list.add(Long.parseLong(matcher.group()));
        }
        return list;
    }

}
