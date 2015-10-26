package javase01.t02;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Math.pow;
import static java.lang.System.out;

/**
 * Minimum finder.
 *
 * @author Vyacheslav_Lapin
 */
public class MinFinder {

    static double findMin(double p, double e) {
        return new MinFinder(p, e).findMin();
    }

    double p, e;

    MinFinder(double p, double e) {
        this.p = p;
        this.e = e;
    }

    static double getA(int n) {
        return 1 / pow(n + 1, 2);
    }

    boolean filter(double a) {
        return a < e;
    }

    double findMin() {
        double min = MAX_VALUE,
               a;

        for (int index = 1; index <= p;)
            if (filter(a = getA(index++)))
                out.println((a < min) ? min = a: a);

        return min;
    }
}
