/**
 * Copyright (c) 2012 Ayan Dave http://daveayan.com
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and 
 * associated documentation files (the "Software"), to deal in the Software without restriction, including 
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the 
 * following conditions:
 * 
 * 1) The above copyright notice and this permission notice shall be included without any changes or alterations 
 * in all copies or substantial portions of the Software.
 * 2) This software shall be used for Good, not Evil.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
**/
package daveayan.lang;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
/** @author daveayan */
/**
 * Implementation of java.util.List that represents the Null List
 */
public class NullList<E> implements List<E> {

	public boolean add(E e) {
		throw new AssertionError("Operation add(E e) not allowed on a Null List");
	}

	public void add(int index, E element) {
		throw new AssertionError("Operation add(int index, E element) not allowed on a Null List");
	}

	public boolean addAll(Collection< ? extends E> c) {
		throw new AssertionError("Operation addAll(Collection< ? extends E> c) not allowed on a Null List");
	}

	public boolean addAll(int index, Collection< ? extends E> c) {
		throw new AssertionError("Operation addAll(int index, Collection< ? extends E> c) not allowed on a Null List");
	}

	public void clear() {
		throw new AssertionError("Operation clear() not allowed on a Null List");
	}

	public boolean contains(Object o) {
		throw new AssertionError("Operation contains(Object o) not allowed on a Null List");
	}

	public boolean containsAll(Collection< ? > c) {
		throw new AssertionError("Operation containsAll(Collection< ? > c) not allowed on a Null List");
	}

	public E get(int index) {
		throw new AssertionError("Operation get(int index) not allowed on a Null List");
	}

	public int indexOf(Object o) {
		throw new AssertionError("Operation indexOf(Object o) not allowed on a Null List");
	}

	public boolean isEmpty() {
		throw new AssertionError("Operation isEmpty() not allowed on a Null List");
	}

	public Iterator<E> iterator() {
		throw new AssertionError("Operation iterator() not allowed on a Null List");
	}

	public int lastIndexOf(Object o) {
		throw new AssertionError("Operation lastIndexOf(Object o) not allowed on a Null List");
	}

	public ListIterator<E> listIterator() {
		throw new AssertionError("Operation listIterator() not allowed on a Null List");
	}

	public ListIterator<E> listIterator(int index) {
		throw new AssertionError("Operation listIterator(int index) not allowed on a Null List");
	}

	public boolean remove(Object o) {
		throw new AssertionError("Operation remove(Object o) not allowed on a Null List");
	}

	public E remove(int index) {
		throw new AssertionError("Operation remove(int index) not allowed on a Null List");
	}

	public boolean removeAll(Collection< ? > c) {
		throw new AssertionError("Operation removeAll(Collection< ? > c) not allowed on a Null List");
	}

	public boolean retainAll(Collection< ? > c) {
		throw new AssertionError("Operation retainAll(Collection< ? > c) not allowed on a Null List");
	}

	public E set(int index, E element) {
		throw new AssertionError("Operation set(int index, E element) not allowed on a Null List");
	}

	public int size() {
		throw new AssertionError("Operation size() not allowed on a Null List");
	}

	public List<E> subList(int fromIndex, int toIndex) {
		throw new AssertionError("Operation subList(int fromIndex, int toIndex) not allowed on a Null List");
	}

	public Object[] toArray() {
		throw new AssertionError("Operation toArray() not allowed on a Null List");
	}

	public <T> T[] toArray(T[] a) {
		throw new AssertionError("Operation toArray(T[] a) not allowed on a Null List");
	}
}