package daveayan.gherkinsalad.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import daveayan.lang.NullList;

public class Elements {
	private List<Element> _elements = new ArrayList<Element>();
	
	public List<Element> _nativeList() {
		return _elements;
	}
	
	public static Elements nullInstance() {
		Elements elements = new Elements();
		elements._elements = new NullList<Element>();
		return elements;
	}
	
	public static Elements instanceOf(List<Element> e) {
		Elements elements = new Elements();
		if(e != null) {
			elements._elements = new ArrayList<Element>(e);
		}
		return elements;
	}
	
	public boolean add(Element element1) {
		return _elements.add(element1);
	}

	public void add(int location, Element element) {
		_elements.add(location, element);
	}

	public boolean addAll(Collection< ? extends Element> element1) {
		return _elements.addAll(element1);
	}

	public boolean addAll(int element1, Collection< ? extends Element> element2) {
		return _elements.addAll(element1, element2);
	}

	public void clear() {
		_elements.clear();
	}

	public boolean contains(Object element1) {
		return _elements.contains(element1);
	}

	public boolean containsAll(Collection< ? > element1) {
		return _elements.containsAll(element1);
	}

	public Element get(int element1) {
		return _elements.get(element1);
	}

	public int indexOf(Object element1) {
		return _elements.indexOf(element1);
	}

	public boolean isEmpty() {
		return _elements.isEmpty();
	}

	public Iterator<Element> iterator() {
		return _elements.iterator();
	}

	public int lastIndexOf(Object element1) {
		return _elements.lastIndexOf(element1);
	}

	public ListIterator<Element> listIterator() {
		return _elements.listIterator();
	}

	public ListIterator<Element> listIterator(int element1) {
		return _elements.listIterator(element1);
	}

	public boolean remove(Object element1) {
		return _elements.remove(element1);
	}

	public Element remove(int element1) {
		return _elements.remove(element1);
	}

	public boolean removeAll(Collection< ? > element1) {
		return _elements.removeAll(element1);
	}

	public boolean retainAll(Collection< ? > element1) {
		return _elements.retainAll(element1);
	}

	public Element set(int element1, Element element2) {
		return _elements.set(element1, element2);
	}

	public int size() {
		return _elements.size();
	}

	public List<Element> subList(int element1, int element2) {
		return _elements.subList(element1, element2);
	}

	public Object[] toArray() {
		return _elements.toArray();
	}

	public <T> T[] toArray(T[] element1) {
		return _elements.toArray(element1);
	}
}