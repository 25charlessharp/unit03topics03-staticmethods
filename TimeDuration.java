import java.util.Scanner;
public class TimeDuration {
    private int seconds;
    private int minutes;
    private int hours;
    private static int numberofDurations = 0;

    public TimeDuration(int seconds, int minutes, int hours){
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;

        numberofDurations ++;
    }

    public int getHours(){
        return hours;
    }

    public int getMinutes(){
        return minutes;
    }

    public int getSeconds(){
        return seconds;
    }

    public static int getNumberofDurations(){
        return numberofDurations;
    }
    
    public String toString(){
        String result = "";
        if (hours < 10){
            result += "0";
        }
        result += hours;
        result += ":";
        if (minutes < 10){
            result += "0";
        }
        result += minutes;
        result += ":";
        if (seconds < 10){
            result += "0";
        }
        result += seconds;
        return result;
    }

    public static TimeDuration parseFromString(String str){
        int searchForColon = 0;
        for(int index = 0; index < str.length(); index ++){
            if(str.substring(index, index + 1).equals(":")){
                searchForColon ++;
            }
        }
        if(searchForColon == 1){
            String minutes = str.substring(0,str.indexOf(":"));
            String seconds = str.substring(str.indexOf(":") + 1, str.length());

            int min = Integer.parseInt(minutes);
            int sec = Integer.parseInt(seconds);
            int hour = 0;
            if(sec >= 60){
                min += sec / 60;
                sec = sec % 60;
            }
            if(min >= 60){
                hour += min / 60;
                min = min % 60;
            }
            if(hour >= 24){
                System.err.println("That is not a time in the day");
                return null;
            }

            TimeDuration result = new TimeDuration(sec,min,hour);
            return result;
        }
        else if (searchForColon == 2){
            String hours = str.substring(0,str.indexOf(":"));
            String minutes = str.substring(str.indexOf(":") + 1, str.lastIndexOf(":"));
            String seconds = str.substring(str.lastIndexOf(":") + 1, str.length());

            int min = Integer.parseInt(minutes);
            int sec = Integer.parseInt(seconds);
            int hour = Integer.parseInt(hours);
            if(sec >= 60){
                min += sec / 60;
                sec = sec % 60;
            }
            if(min >= 60){
                hour += min / 60;
                min = min % 60;
            }
            if(hour >= 24){
                System.err.println("That is not a time in the day");
                return null;
            }

            TimeDuration result = new TimeDuration(sec,min,hour);
            return result;
        }
        else{
            System.err.println("Could not parse that information, it should look like:");
            System.err.println("15:42:09");
            return null;
        }
        }
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String response = "yes";

        while (response.equals("yes")){
            System.out.println("Enter a time duration in the format: 00:00:00");
            
            String input = scanner.nextLine();
            TimeDuration newTD = parseFromString(input);
            if(newTD == null){
                System.out.println("Sorry, but I couldn't parse your string");
                System.out.println("If you want to continue, enter yes");
                response = scanner.nextLine();
            }
            else{
            System.out.println(newTD);
            System.out.println("Enter yes if you want to continue");
            response = scanner.nextLine();
            }
        }
        scanner.close();
        System.out.println("Over the session, you have created " + numberofDurations + " time(s)");

    }
}
