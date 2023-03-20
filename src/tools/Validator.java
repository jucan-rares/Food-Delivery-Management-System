package tools;

import java.time.LocalDateTime;


public class Validator {

    // Will return null if all the parsing is correct
    public static Exception validateNumberTextFields(String s1, String s2, String s3, String s4, String s5, String s6)  {

        if ( s1.isBlank() || s2.isBlank() || s3.isBlank() ||
                s4.isBlank() || s5.isBlank() || s6.isBlank()){
            return new Exception("Invalid fields");
        }

        // s1 - double
        try{
            if ( (Double.parseDouble(s1) < 0) || (Double.parseDouble(s1) > 5) ){
                return new Exception("Invalid rating range.");
            }
        }catch (NumberFormatException | NullPointerException exp){
            return new Exception("Invalid rating.");
        }

        // s2 - int
        try{
            if ( Integer.parseInt(s2) < 0 ){
                return new Exception("Negative number encountered...");
            }

        }catch (NumberFormatException | NullPointerException exp){
            return new Exception("Invalid calories.");
        }

        // s3 - int
        try{
            if ( Integer.parseInt(s3) < 0 ){
                return new Exception("Negative number encountered...");
            }

        }catch (NumberFormatException | NullPointerException exp){
            return new Exception("Invalid protein.");
        }

        // s4 - int
        try{
            if ( Integer.parseInt(s4) < 0 ){
                return new Exception("Negative number encountered...");
            }

        }catch (NumberFormatException | NullPointerException exp){
            return new Exception("Invalid fat.");
        }

        // s5 - int
        try{
            if ( Integer.parseInt(s5) < 0 ){
                return new Exception("Negative number encountered...");
            }

        }catch (NumberFormatException | NullPointerException exp){
            return new Exception("Invalid sodium.");
        }

        // s6 - int
        try{
            if ( Integer.parseInt(s6) < 0 ){
                return new Exception("Negative number encountered...");
            }

        }catch (NumberFormatException | NullPointerException exp){
            return new Exception("Invalid price.");
        }

        return null;
    }

    public static Boolean stringValidate(String string){
        return (string.isBlank());
    }

    public static Boolean intValidate(String string){
        return ( stringValidate(string) && Integer.parseInt(string) <= 0 );
    }

//    public static Boolean hourValidate( Integer hour ){
//        return ( (hour >= 0) && (hour <= 24) );
//    }

    public static int validateHour(String hour) {
        try {
            String[] sHour = hour.split(":");
            if(sHour.length != 2) {
                return -1;
            }
            int hours = Integer.parseInt(sHour[0]);
            if (hours < 0 || hours > 23) {
                return -1;
            }
            int minutes = Integer.parseInt(sHour[1]);
            if (minutes < 0 || minutes > 59) {
                return -1;
            }
            return hours * 60 + minutes;
        } catch (NumberFormatException exception) {
            return -1;
        }
    }

    public static int[] validateDate(String dateString) {
        int[] dateSeparated = new int[3];
        int[] nothing = new int[0];
        try {
            String[] DMY = dateString.split("/");
            if (DMY.length != 3) {
                return nothing;
            }
            int day = Integer.parseInt(DMY[0]);
            int month = Integer.parseInt(DMY[1]);
            int year = Integer.parseInt(DMY[2]);
            if (day < 1 || day > 31 || month < 1 || month > 12 || year < 0 || year > LocalDateTime.now().getYear()) {
                return nothing;
            }
            if (year == LocalDateTime.now().getYear())
                if (month > LocalDateTime.now().getMonthValue() || (month == LocalDateTime.now().getMonthValue() &&
                        day > LocalDateTime.now().getDayOfMonth())) {
                    return nothing;
                }

            dateSeparated[0] = day;
            dateSeparated[1] = month;
            dateSeparated[2] = year;
            return dateSeparated;
        } catch (NumberFormatException exception) {
            return nothing;
        }
    }

//    public static Boolean dateValidate( String date ){
//
//    }

}

