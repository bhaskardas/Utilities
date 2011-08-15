/*
 * Many a times there arises a requirement to return multiple objects from a
 * method. This class is part of a tuple library. It class can be used
 * to return at the max two objects simultaneously.
 * It acts as a container for any kind of objects.
 * A single instance of this class is capable of holding two objects at a
 * time.
 * In order to return more than two objects at a time, just extends this class
 * to create to a ThreeTuple class, which would be capable of returning three
 * objects simultaneously.
 *
 * This tuple library is just a facilitator for those hard times when the
 * requirement is to return multiple objects from a method. Therefore, use it
 * wisely.
 */

package org.utilities.util.tuplelibrary;

/**
 * @author bhaskar
 * @created on 22 Sep 2010
 * @version 1.0
 * @changeLog
 */
public class TwoTuple<One, Two> {

    public final One one;
    public final Two two;

    public TwoTuple(One one, Two two) {
        this.one = one;
        this.two = two;
    }
}
