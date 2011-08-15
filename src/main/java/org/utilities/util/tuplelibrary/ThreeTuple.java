/*
 * Many a times there arises a requirement to return multiple objects from a
 * method. This class is part of a tuple library. It class can be used
 * to return at the max three objects simultaneously.
 * It acts as a container for any kind of objects.
 * A single instance of this class is capable of holding three objects at a
 * time.
 * In order to return more than three objects at a time, just extends this class
 * to create a FourTuple class, which would be capable of returning four
 * objects simultaneously. But before making a FourTuple library, first revisit
 * or rethink your logic, as returning four objects from a function or method
 * is not a good idea. This means that your function is doing multiple things
 * simultaneously, whichcan be broken down into simpler things.
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
public class ThreeTuple<One, Two, Three> extends TwoTuple<Object, Object>{

    public final Three three;

    public ThreeTuple(One one, Two two, Three three) {
        super(one, two);
        this.three = three;
    }
}
