package Amazon;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddress {

    public static int numUniqueEmails(String[] emails) {
        if(emails == null || emails.length == 0) {
            return 0;
        }

        Set<String> seen = new HashSet();
        for (String email: emails) {
            int i = email.indexOf('@');
            String local = email.substring(0, i);
            String rest = email.substring(i);
            if (local.contains("+")) {
                local = local.substring(0, local.indexOf('+'));
            }
            local = local.replaceAll("\\.", "");
            seen.add(local + "@" + rest);
        }

        return seen.size();
    }


    public static void main(String[] args) {
        String[] emails = {"testemail@leetcode.com","testemail1@leetcode.com","testemail+david@lee.tcode.com"};
        System.out.println(numUniqueEmails(emails));
    }
}
