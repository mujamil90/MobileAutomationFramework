package com.ui.qa.genes.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 
 * @author Muzzamil
 *
 */
public class Time {

    public static String getCurrentTimeAndDate() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("E yyyy/MM/dd 'at' hh:mm:ss a");
        return ft.format(dNow);
    }

    public static String getTimeStampAndDate() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yy-MM-dd_kk.mm.ss");
        return ft.format(dNow);
    }
}
