package Database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    public static String convertToSQLDateFormat(String inputDate) {
        String outputDate = null;
        DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat outputFormat = new SimpleDateFormat("dd-MMM-yy");
        try {
            Date date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }
}
