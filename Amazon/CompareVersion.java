package Amazon;

public class CompareVersion {

    public static int compareVersion(String version1, String version2) {
        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");

        int idx1 = 0;
        int idx2 = 0;
        while(idx1 < split1.length && idx2 < split2.length) {
            int value1 = Integer.parseInt(split1[idx1++]);
            int value2 = Integer.parseInt(split2[idx2++]);
            if(value1 < value2) {
                return -1;
            }else if(value1 > value2) {
                return 1;
            }
        }

        while(idx1 < split1.length) {
            if(Integer.parseInt(split1[idx1++]) > 0) {
                return 1;
            }
        }

        while(idx2 < split2.length) {
            if(Integer.parseInt(split2[idx2++]) > 0) {
                return -1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        String version1 = "1.1";
        String version2 = "1.0";
        System.out.println(compareVersion(version1, version2));
    }
}
