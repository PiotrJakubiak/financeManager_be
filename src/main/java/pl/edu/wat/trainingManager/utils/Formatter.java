package pl.edu.wat.trainingManager.utils;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class Formatter {

    public java.util.Date stringToDate(String date) throws ParseException {

        DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy");
        return formatter.parse(date);
    }
}
