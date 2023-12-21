package me.restonic4.fading_realms.util.Camera.Cutscene;

import java.io.Serializable;

public class Easing {
    private String easing;

    public Easing() {
    }

    public Easing(String name) {
        this.easing = name;
    }

    public Easing linear() {
        this.easing = "linear";
        return this;
    }

    public Easing sineInOut() {
        this.easing = "sineInOut";
        return this;
    }

    public Easing sineIn() {
        this.easing = "sineIn";
        return this;
    }

    public Easing sineOut() {
        this.easing = "sineOut";
        return this;
    }

    public Easing quadInOut() {
        this.easing = "quadInOut";
        return this;
    }

    public Easing quadIn() {
        this.easing = "quadIn";
        return this;
    }

    public Easing quadOut() {
        this.easing = "quadOut";
        return this;
    }

    public Easing cubicInOut() {
        this.easing = "cubicInOut";
        return this;
    }

    public Easing cubicIn() {
        this.easing = "cubicIn";
        return this;
    }

    public Easing cubicOut() {
        this.easing = "cubicOut";
        return this;
    }

    public Easing quartInOut() {
        this.easing = "quartInOut";
        return this;
    }

    public Easing quartIn() {
        this.easing = "quartIn";
        return this;
    }

    public Easing quartOut() {
        this.easing = "quartOut";
        return this;
    }

    public Easing quintInOut() {
        this.easing = "quintInOut";
        return this;
    }

    public Easing quintIn() {
        this.easing = "quintIn";
        return this;
    }

    public Easing quintOut() {
        this.easing = "quintOut";
        return this;
    }

    public Easing expoInOut() {
        this.easing = "expoInOut";
        return this;
    }

    public Easing expoIn() {
        this.easing = "expoIn";
        return this;
    }

    public Easing expoOut() {
        this.easing = "expoOut";
        return this;
    }

    public Easing circInOut() {
        this.easing = "circInOut";
        return this;
    }

    public Easing circIn() {
        this.easing = "circIn";
        return this;
    }

    public Easing circOut() {
        this.easing = "circOut";
        return this;
    }

    public Easing backInOut() {
        this.easing = "backInOut";
        return this;
    }

    public Easing backIn() {
        this.easing = "backIn";
        return this;
    }

    public Easing backOut() {
        this.easing = "backOut";
        return this;
    }

    public Easing elasticInOut() {
        this.easing = "elasticInOut";
        return this;
    }

    public Easing elasticIn() {
        this.easing = "elasticIn";
        return this;
    }

    public Easing elasticOut() {
        this.easing = "elasticOut";
        return this;
    }

    public Easing bounceInOut() {
        this.easing = "bounceInOut";
        return this;
    }

    public Easing bounceIn() {
        this.easing = "bounceIn";
        return this;
    }

    public Easing bounceOut() {
        this.easing = "bounceOut";
        return this;
    }

    public static double linearEasing(double t) {
        return t;
    }

    public static double sineInEasing(double t) {
        return 1 - Math.cos((t * Math.PI) / 2);
    }

    public static double sineOutEasing(double t) {
        return Math.sin((t * Math.PI) / 2);
    }

    public static double sineInOutEasing(double t) {
        return -(Math.cos(Math.PI * t) - 1) / 2;
    }

    public static double quadInEasing(double t) {
        return t * t;
    }

    public static double quadOutEasing(double t) {
        return 1 - (1 - t) * (1 - t);
    }

    public static double quadInOutEasing(double t) {
        return t < 0.5 ? 2 * t * t : 1 - Math.pow(-2 * t + 2, 2) / 2;
    }

    public static double cubicInEasing(double t) {
        return t * t * t;
    }

    public static double cubicOutEasing(double t) {
        return 1 - Math.pow(1 - t, 3);
    }

    public static double cubicInOutEasing(double t) {
        return t < 0.5 ? 4 * t * t * t : 1 - Math.pow(-2 * t + 2, 3) / 2;
    }

    public static double quartInEasing(double t) {
        return t * t * t * t;
    }

    public static double quartOutEasing(double t) {
        return 1 - Math.pow(1 - t, 4);
    }

    public static double quartInOutEasing(double t) {
        return t < 0.5 ? 8 * t * t * t * t : 1 - Math.pow(-2 * t + 2, 4) / 2;
    }

    public static double quintInEasing(double t) {
        return t * t * t * t * t;
    }

    public static double quintOutEasing(double t) {
        return 1 - Math.pow(1 - t, 5);
    }

    public static double quintInOutEasing(double t) {
        return t < 0.5 ? 16 * t * t * t * t * t : 1 - Math.pow(-2 * t + 2, 5) / 2;
    }

    public static double expoInEasing(double t) {
        return t == 0 ? 0 : Math.pow(2, 10 * t - 10);
    }

    public static double expoOutEasing(double t) {
        return t == 1 ? 1 : 1 - Math.pow(2, -10 * t);
    }

    public static double expoInOutEasing(double t) {
        if (t == 0) {
            return 0;
        } else if (t == 1) {
            return 1;
        } else if (t < 0.5) {
            return Math.pow(2, 20 * t - 10) / 2;
        } else {
            return (2 - Math.pow(2, -20 * t + 10)) / 2;
        }

    }

    public static double circInEasing(double t) {
        return 1 - Math.sqrt(1 - Math.pow(t, 2));
    }

    public static double circOutEasing(double t) {
        return Math.sqrt(1 - Math.pow(t - 1, 2));
    }

    public static double circInOutEasing(double t) {
        if (t < 0.5) {
            return (1 - Math.sqrt(1 - Math.pow(2 * t, 2))) / 2;
        } else {
            return (Math.sqrt(1 - Math.pow(-2 * t + 2, 2)) + 1) / 2;
        }

    }

    public static double backInEasing(double t) {
        final double c1 = 1.70158;
        final double c3 = c1 + 1;

        return c3 * t * t * t - c1 * t * t;
    }

    public static double backOutEasing(double t) {
        final double c1 = 1.70158;
        final double c3 = c1 + 1;

        return 1 + c3 * Math.pow(t - 1, 3) + c1 * Math.pow(t - 1, 2);

    }

    public static double backInOutEasing(double t) {
        final double c1 = 1.70158;
        final double c2 = c1 * 1.525;

        if (t < 0.5) {
            return (Math.pow(2 * t, 2) * ((c2 + 1) * 2 * t - c2)) / 2;
        } else {
            return (Math.pow(2 * t - 2, 2) * ((c2 + 1) * (t * 2 - 2) + c2) + 2) / 2;
        }

    }

    public static double elasticInEasing(double t) {
        final double c4 = (2 * Math.PI) / 3;

        if (t == 0) {
            return 0;
        } else if (t == 1) {
            return 1;
        } else {
            return -Math.pow(2, 10 * t - 10) * Math.sin((t * 10 - 10.75) * c4);
        }

    }

    public static double elasticOutEasing(double t) {
        final double c4 = (2 * Math.PI) / 3;

        if (t == 0) {
            return 0;
        } else if (t == 1) {
            return 1;
        } else {
            return Math.pow(2, -10 * t) * Math.sin((t * 10 - 0.75) * c4) + 1;
        }

    }

    public static double elasticInOutEasing(double t) {
        final double c5 = (2 * Math.PI) / 4.5;

        if (t == 0) {
            return 0;
        } else if (t == 1) {
            return 1;
        } else {
            double sin = Math.sin((20 * t - 11.125) * c5);

            if (t < 0.5) {
                return -(Math.pow(2, 20 * t - 10) * sin) / 2;
            } else {
                return (Math.pow(2, -20 * t + 10) * sin) / 2 + 1;
            }
        }

    }

    public static double bounceInEasing(double t) {
        return 1 - bounceOutEasing(1 - t);
    }

    public static double bounceOutEasing(double t) {
        final double n1 = 7.5625;
        final double d1 = 2.75;

        if (t < 1 / d1) {
            return n1 * t * t;
        } else if (t < 2 / d1) {
            t -= 1.5 / d1;
            return n1 * t * t + 0.75;
        } else if (t < 2.5 / d1) {
            t -= 2.25 / d1;
            return n1 * t * t + 0.9375;
        } else {
            t -= 2.625 / d1;
            return n1 * t * t + 0.984375;
        }

    }

    public static double bounceInOutEasing(double t) {
        return t < 0.5 ? (1 - bounceOutEasing(1 - 2 * t)) / 2 : (1 + bounceOutEasing(2 * t - 1)) / 2;
    }

    public double apply(double t) {
        return switch (easing) {
            case "linear" -> linearEasing(t);
            case "sineInOut" -> sineInOutEasing(t);
            case "sineIn" -> sineInEasing(t);
            case "sineOut" -> sineOutEasing(t);
            case "quadInOut" -> quadInOutEasing(t);
            case "quadIn" -> quadInEasing(t);
            case "quadOut" -> quadOutEasing(t);
            case "cubicInOut" -> cubicInOutEasing(t);
            case "cubicIn" -> cubicInEasing(t);
            case "cubicOut" -> cubicOutEasing(t);
            case "quartInOut" -> quartInOutEasing(t);
            case "quartIn" -> quartInEasing(t);
            case "quartOut" -> quartOutEasing(t);
            case "quintInOut" -> quintInOutEasing(t);
            case "quintIn" -> quintInEasing(t);
            case "quintOut" -> quintOutEasing(t);
            case "expoInOut" -> expoInOutEasing(t);
            case "expoIn" -> expoInEasing(t);
            case "expoOut" -> expoOutEasing(t);
            case "circInOut" -> circInOutEasing(t);
            case "circIn" -> circInEasing(t);
            case "circOut" -> circOutEasing(t);
            case "backInOut" -> backInOutEasing(t);
            case "backIn" -> backInEasing(t);
            case "backOut" -> backOutEasing(t);
            case "elasticInOut" -> elasticInOutEasing(t);
            case "elasticIn" -> elasticInEasing(t);
            case "elasticOut" -> elasticOutEasing(t);
            case "bounceInOut" -> bounceInOutEasing(t);
            case "bounceIn" -> bounceInEasing(t);
            case "bounceOut" -> bounceOutEasing(t);
            default -> throw new IllegalArgumentException("Easing type not found: " + easing);
        };
    }

    public String toString() {
        return easing;
    }

    public String generateJavaCode() {
        return "new Easing()." + easing + "()";
    }
}
