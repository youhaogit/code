package Amazon;

import java.util.ArrayList;
import java.util.List;

public class PascalTrangleII {

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        if(rowIndex == 0) {
            row.add(1);
            return row;
        }

        List<Integer> lastRow = getRow(rowIndex - 1);
        for(int j = 0; j <= rowIndex; j++) {
            if(j == 0 || j == rowIndex) {
                row.add(1);
            }else {
                row.add(lastRow.get(j - 1) + lastRow.get(j));
            }
        }

        return row;
    }

    public static void main(String[] args) {
        System.out.println(getRow(11));
    }
}
