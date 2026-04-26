/*
Class containing implementations of all optimized functions and their parameter constraints.
 */
public class Functions {
    public static final double BUKIN_X_MAX = -5.0;
    public static final double BUKIN_X_MIN = -15.0;
    public static final double BUKIN_Y_MAX = 3.0;
    public static final double BUKIN_Y_MIN = -3.0;

    public static final double MATYAS_X_MAX = 10.0;
    public static final double MATYAS_X_MIN = -10.0;
    public static final double MATYAS_Y_MAX = 10.0;
    public static final double MATYAS_Y_MIN = -10.0;

    public static final double LEVI_X_MAX = 10.0;
    public static final double LEVI_X_MIN = -10.0;
    public static final double LEVI_Y_MAX = 10.0;
    public static final double LEVI_Y_MIN = -10.0;

    public static final double HIMMELBLAU_X_MAX = 5.0;
    public static final double HIMMELBLAU_X_MIN = -5.0;
    public static final double HIMMELBLAU_Y_MAX = 5.0;
    public static final double HIMMELBLAU_Y_MIN = -5.0;

    public static final double THREE_HUMP_CAMEL_X_MAX = 5.0;
    public static final double THREE_HUMP_CAMEL_X_MIN = -5.0;
    public static final double THREE_HUMP_CAMEL_Y_MAX = 5.0;
    public static final double THREE_HUMP_CAMEL_Y_MIN = -5.0;

    public static final double EASOM_X_MAX = 100.0;
    public static final double EASOM_X_MIN = -100.0;
    public static final double EASOM_Y_MAX = 100.0;
    public static final double EASOM_Y_MIN = -100.0;


    // f(-10, 1) = 0
    public static double bukin(double x, double y) {
        return 100*Math.sqrt(Math.abs(y-0.01*Math.pow(x, 2))) + 0.01*Math.abs(x+10);
    }

    // f(0, 0) = 0
    public static double matyas(double x, double y) {
        return 0.26*(Math.pow(x, 2) + Math.pow(y, 2)) - 0.48*x*y;
    }

    // f(1, 1) = 0
    public static double levi(double x, double y) {
        return Math.pow(Math.sin(3*Math.PI*x), 2)
                + Math.pow(x-1, 2)*(1+Math.pow(Math.sin(3*Math.PI*y), 2))
                + Math.pow(y-1, 2)*(1+Math.pow(Math.sin(2*Math.PI*y), 2));
    }

    // f(3.0, 2.0) = 0.0, f(-2.805118, 3.131312) = 0.0, f(-3779310, -3283186) = 0.0, f(3.584428, -1.848126) = 0.0
    public static double himmelblau(double x, double y) {
        return Math.pow(Math.pow(x, 2)+y-11, 2) + Math.pow(x+Math.pow(y, 2)-7, 2);
    }

    // f(0, 0) = 0
    public static double threeHumpCamel(double x, double y) {
        return 2*Math.pow(x, 2) - 1.05*Math.pow(x, 4) + Math.pow(x, 6)/6 + x*y + Math.pow(y, 2);
    }

    // f(pi, pi) = -1
    public static double easom(double x, double y) {
        return -Math.cos(x) * Math.cos(y) * Math.exp(-(Math.pow(x-Math.PI, 2)+Math.pow(y-Math.PI, 2)));
    }
}