package com.wavefront;

import java.awt.*;

public class Util {

    /**
     * check four points are overlapping
     * @param l1
     * @param r1
     * @param l2
     * @param r2
     * @return
     */
    public static boolean overlap(Point l1, Point r1, Point l2, Point r2) {
        if (l1.x > r2.x || l2.x > r1.x) {
            return false;
        }

        // If one rectangle is above other
        if (l1.y < r2.y || l2.y < r1.y) {
            return false;
        }

        return true;
    }

}
