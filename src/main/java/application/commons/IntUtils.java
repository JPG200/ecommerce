package application.commons;

public class IntUtils {
    public static boolean nonBlankBP(double value){
        double trimmed = value;
        return trimmed >= 0.00;
    }

    public static boolean nonBlankId(int value){
        int trimmed = value;
        return trimmed >= 1;
    }

    public static boolean nonBlankTaxes(double value){
        double trimmed = value;
        return trimmed <= 1.00 || trimmed >= 0.00;
    }

    public static boolean nonBlankCI(int value){
        int trimmed = value;
        return trimmed >= 0;
    }
}
