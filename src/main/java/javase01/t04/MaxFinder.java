package javase01.t04;

/**
 * MaxFinder.
 *
 * @author Vyacheslav_Lapin
 */
public class MaxFinder {

    private double[] as;

    public MaxFinder(double[] as) {
        this.as = as;
    }

    public double getMaximum() {
        double max = Double.NEGATIVE_INFINITY,
               postA = max;
        for (double a: as) {
            if (postA + a > max)
                max = postA + a;
            postA = a;
        }
        return max;
    }
}
