package ch.dams333.multiGames.utils.time;

public class TimeUtils {

    public static String secondToString(int second, String separator, boolean completeTwoNumbers){
        if(second < 60){
            if(second < 10){
                if(completeTwoNumbers){
                    return "0" + String.valueOf(second);
                }else{
                    return String.valueOf(second);
                }
            }else{
                return String.valueOf(second);
            }
        }else if(second < 3600){
            int min = (second % 3600) / 60;
            int sec = second % 60;

            String minStr = "";
            String secStr = "";

            if(min < 10){
                if(completeTwoNumbers){
                    minStr = "0" + String.valueOf(min);
                }else{
                    minStr = String.valueOf(min);
                }
            }else{
                minStr = String.valueOf(min);
            }

            if(sec < 10){
                if(completeTwoNumbers){
                    secStr = "0" + String.valueOf(sec);
                }else{
                    secStr = String.valueOf(sec);
                }
            }else{
                secStr = String.valueOf(sec);
            }

            return minStr + separator + secStr;
        }else{
            int sec = second % 60;
            int hour = second / 60;
            int min = hour % 60;
            hour = hour/60;

            String hourStr = "";
            String minStr = "";
            String secStr = "";

            if(min < 10){
                if(completeTwoNumbers){
                    minStr = "0" + String.valueOf(min);
                }else{
                    minStr = String.valueOf(min);
                }
            }else{
                minStr = String.valueOf(min);
            }

            if(sec < 10){
                if(completeTwoNumbers){
                    secStr = "0" + String.valueOf(sec);
                }else{
                    secStr = String.valueOf(sec);
                }
            }else{
                secStr = String.valueOf(sec);
            }

            if(hour < 10){
                if(completeTwoNumbers){
                    hourStr = "0" + String.valueOf(hour);
                }else{
                    hourStr = String.valueOf(hour);
                }
            }else{
                hourStr = String.valueOf(hour);
            }

            return hourStr + separator + minStr + separator + secStr;
        }
    }

    public static String secondToCompleteString(int second, String separator, boolean completeTwoNumbers){
        int sec = second % 60;
        int hour = second / 60;
        int min = hour % 60;
        hour = hour/60;

        String hourStr = "";
        String minStr = "";
        String secStr = "";

        if(min < 10){
            if(completeTwoNumbers){
                minStr = "0" + String.valueOf(min);
            }else{
                minStr = String.valueOf(min);
            }
        }else{
            minStr = String.valueOf(min);
        }

        if(sec < 10){
            if(completeTwoNumbers){
                secStr = "0" + String.valueOf(sec);
            }else{
                secStr = String.valueOf(sec);
            }
        }else{
            secStr = String.valueOf(sec);
        }

        if(hour < 10){
            if(completeTwoNumbers){
                hourStr = "0" + String.valueOf(hour);
            }else{
                hourStr = String.valueOf(hour);
            }
        }else{
            hourStr = String.valueOf(hour);
        }

        return hourStr + separator + minStr + separator + secStr;
    }
    
}
