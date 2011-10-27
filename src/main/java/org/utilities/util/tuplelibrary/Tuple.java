package org.utilities.util.tuplelibrary;

/**
 * 
 * @author bhaskar
 * @created on 02 Oct 2011
 * @version 1.0
 * @changeLog
 */
public class Tuple {
	/**
	 * 
	 * @param <One>
	 * @param <Two>
	 * @param one
	 * @param two
	 * @return
	 */
	public static <One, Two> TwoTuple<One, Two> tuple(One one, Two two){
		return new TwoTuple<One, Two>(one, two);
	}
	
	/**
	 * 
	 * @param <One>
	 * @param <Two>
	 * @param <Three>
	 * @param one
	 * @param two
	 * @param three
	 * @return
	 */
	public static <One, Two, Three> ThreeTuple<One, Two, Three> tuple(One one, Two two, Three three){
		return new ThreeTuple<One, Two, Three>(one, two, three);
	}
	
	/**
	 * 
	 * @param <One>
	 * @param <Two>
	 * @param <Three>
	 * @param <Four>
	 * @param one
	 * @param two
	 * @param three
	 * @param four
	 * @return
	 */
	public static <One, Two, Three, Four> FourTuple<One, Two, Three, Four> tuple(One one, Two two, Three three, Four four){
		return new FourTuple<One, Two, Three, Four>(one, two, three, four);
	}
}