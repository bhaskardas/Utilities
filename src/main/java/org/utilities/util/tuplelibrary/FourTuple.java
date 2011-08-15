/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.utilities.util.tuplelibrary;

/**
 * @author bhaskar
 * @created on 27 Feb 2011
 * @version 1.0
 * @changeLog
 */
public class FourTuple<One, Two, Three, Four> extends ThreeTuple<Object, Object, Object>{
    public final Four four;

    public FourTuple(One one, Two two, Three three, Four four) {
        super(one, two, three);
        this.four = four;
    }
}
