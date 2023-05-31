package inputData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class inputData {
    public List<Float> input() {
        List<Float> row;
        readFile read = new readFile();
        File file = new File("src/row2");
        try {
            row = new ArrayList<>(read.readFileIntoList(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Float> row_sort = new ArrayList<>(row);
        row_sort.sort(Comparator.naturalOrder());
        return row_sort;
    }

}

