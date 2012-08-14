package daveayan.gherkinsalad.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.NullElement;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import daveayan.gherkinsalad.components.functions.GetTextFunction;
import daveayan.lang.NullList;

public class Elements {
	private List<Element> _elements = new ArrayList<Element>();
	
	public void each(Predicate<Element> predicate) {
		for(Element e: _elements) {
			predicate.apply(e);
		}
	}
	
	public List<String> asString() {
		List<String> strings = new ArrayList<String>();
		for(Element e: _elements) {
			strings.add(e.getText());
		}
//		return Lists.transform(_elements, GetTextFunction.apply);
		return strings;
	}
	
	public List<?> each(Function<Element, ?> function) {
		return Lists.transform(_elements, function);
	}
	
	public Element findFirstElementThatMatches(Predicate<Element> match) {
		if(_elements != null) {
			for(Element e: _elements) {
				if(match.apply(e)) {
					return e;
				}
			}
		}
		return new NullElement();
	}
	
	public Element findFirstElementWithText(String expectedText) {
		if(_elements != null) {
			for(Element e: _elements) {
				if(e.has(expectedText)) {
					return e;
				}
			}
		}
		return new NullElement();
	}
	
	public Element findFirstElementWithAllOfTheseTexts(String... expectedTexts) {
		for(Element e: _elements) {
			if(e.hasAllTexts(expectedTexts)) {
				return e;
			}
			System.out.println("Element '" + e + "' does not have texts '" + expectedTexts + "'");
		}
		return new NullElement();
	}
	
	public Element findFirstElementWithAnyOfTheseTexts(String... expectedTexts) {
		for(Element e: _elements) {
			if(e.hasAnyText(expectedTexts)) {
				return e;
			}
		}
		return new NullElement();
	}
	
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
	
	// Methods from java.util.List interface
	
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