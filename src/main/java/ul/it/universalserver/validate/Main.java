package ul.it.universalserver.validate;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date date = new Date();
        Date date1 = new Date(date.getYear(), date.getMonth(), date.getDate() + 45, date.getHours(), date.getMinutes(), date.getSeconds());
        System.out.println(date1);
    }
}
