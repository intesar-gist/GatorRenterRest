package com.gsd.gatorrenter.utils;

import org.apache.log4j.Logger;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Intesar on 3/6/2017.
 */
public class DateUtility {

    private static final Logger LOGGER = Logger.getLogger(DateUtility.class);

    public static Date convertToDate(String dateInString) {

        //2016-15-12
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {

            java.util.Date date = formatter.parse(dateInString);

            Date sqlDate = new Date(date.getTime());

            return sqlDate;

        } catch (ParseException ex) {
            LOGGER.error(ex);
            return null;
        }

    }


}
