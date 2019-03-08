package Amazon;

public class IntegerToEnglishWords {

//    Example 1:
//
//    Input: 123
//    Output: "One Hundred Twenty Three"
//    Example 2:
//
//    Input: 12345
//    Output: "Twelve Thousand Three Hundred Forty Five"
//    Example 3:
//
//    Input: 1234567
//    Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
//    Example 4:
//
//    Input: 1234567891
//    Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"

    public static String one(int num) {
        switch(num) {
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
        }
        return "";
    }

    public static String twoLessThan20(int num) {
        switch(num) {
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            case 14:
                return "Fourteen";
            case 15:
                return "Fifteen";
            case 16:
                return "Sixteen";
            case 17:
                return "Seventeen";
            case 18:
                return "Eighteen";
            case 19:
                return "Nineteen";
        }
        return "";
    }

    public static String ten(int num) {
        switch(num) {
            case 2:
                return "Twenty";
            case 3:
                return "Thirty";
            case 4:
                return "Forty";
            case 5:
                return "Fifty";
            case 6:
                return "Sixty";
            case 7:
                return "Seventy";
            case 8:
                return "Eighty";
            case 9:
                return "Ninety";
        }
        return "";
    }

    public static String two(int num) {
        if (num == 0)
            return "";
        else if (num < 10)
            return one(num);
        else if (num < 20)
            return twoLessThan20(num);
        else {
            int tenner = num / 10;
            int rest = num - tenner * 10;
            if (rest != 0)
                return ten(tenner) + " " + one(rest);
            else
                return ten(tenner);
        }
    }

    public static String three(int num) {
        int hundred = num / 100;
        int rest = num % 100;

        if(hundred != 0 && rest != 0) {
            return one(hundred) + " " + "Hundred" + " " + two(rest);
        }else if(hundred != 0 && rest == 0) {
            return one(hundred) + " " + "Hundred";
        }else if(hundred == 0 && rest != 0) {
            return two(rest);
        }else {
            return "";
        }
    }

    public static String numberToWords(int num) {
        if(num == 0) {
            return "Zero";
        }

        String result = "";

        int billion = num / 1_000_000_000;
        num %= 1_000_000_000;
        if(billion != 0) {
            result += three(billion) + " " + "Billion";
        }

        int million = num / 1_000_000;
        num %= 1_000_000;
        if(million != 0) {
            if(! result.isEmpty())
                result += " ";
            result += three(million) + " " + "Million";
        }

        int thousand = num / 1_000;
        num %= 1_000;
        if(thousand != 0) {
            if (!result.isEmpty())
                result += " ";
            result += three(thousand) + " " + "Thousand";
        }

        if (!result.isEmpty())
            result += " ";
        result += three(num);

        return result.trim();
    }

    public static void main(String[] args) {
        int number = 50868;
        System.out.println(numberToWords(number));
    }
}
