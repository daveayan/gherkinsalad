package daveayan.gherkinsalad.components.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.NullElement;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import daveayan.gherkinsalad.Strings;
import daveayan.gherkinsalad.report.ReportFactory;

public class Elements extends BaseBrowserElement implements Nullable {
	private List<Element> _elements = new ArrayList<Element>();
	
	public void should_have_any_of_these_texts(Strings texts) {
		List<String> available_texts = new ArrayList<String>();
		
		Iterator<String> iter = texts.iterator();
		while(iter.hasNext()) {
			String text = iter.next();
			for(Element element: _nativeList()) {
				available_texts.add(element.getText());
				if(element.getText().contains(text)) {
					ReportFactory.reporter().task("Found '" + text + "' in " + texts);
					return;
				}
			}
		}
		ReportFactory.reporter().error("Did not find any of the texts " + texts + ", found " + available_texts);
	}
	
	public void each_element_should_have_all_of_these_texts(String... texts) {
//		StringBuffer sb = new StringBuffer();
	}
	
	public void should_have_all_of_these_texts(Strings texts) {
		List<String> text_not_available = new ArrayList<String>();
		Iterator<String> iter = texts.iterator();
		while(iter.hasNext()) {
			String text = iter.next();
			boolean found = Boolean.FALSE;
			for(Element element: _nativeList()) {
				if(element.getText().contains(text)) {
					found = Boolean.TRUE;
					break;
				}
			}
			if(! found) {
				text_not_available.add(text);
			}
		}
		if(text_not_available.isEmpty()) {
			ReportFactory.reporter().task("Found all expected texts " + texts);
		} else {
			ReportFactory.reporter().error("Did not find expected texts " + text_not_available + " in " + this);
		}
	}
	
	public String toString() {
		return toStrings().toString();
	}
	
	public Strings toStrings() {
		Strings strings = Strings.new_instance();
		for(Element element: _nativeList()) {
			strings = strings.add(element.getText());
		}
		return strings;
	}
	
	public void each(Predicate<Element> predicate) {
		for(Element e: _elements) {
			predicate.apply(e);
		}
	}
	
	public Elements filter(Predicate<Element> predicate) {
		Elements filtered_elements = new Elements();
		for(Element e: _elements) {
			if(predicate.apply(e)) {
				filtered_elements.add(e);
			}
		}
		return filtered_elements;
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
				if(e.is(expectedText)) {
					return e;
				}
			}
		}
		return new NullElement();
	}
	
	public Element findFirstElementWithPartialText(String expectedText) {
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
	
	public Elements findElementsThatMatch(Predicate<Element> predicate) {
		Elements filtered_elements = new Elements();
		for(Element element: _elements) {
			if(predicate.apply(element)) {
				filtered_elements.add(element);
			}
		}
		return filtered_elements;
	}
	
	public Strings toStrings(Function<Element, String> function) {
		List<String> strings = Lists.transform(_elements, function);
		return Strings.instance_from(strings);
	}
	
	public Element getRandomElement() {
		if(_nativeList() == null) {
			return new NullElement();
		}
		return _nativeList().get(this.random.nextInt(_nativeList().size()));
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

	@SuppressWarnings("unchecked")
	public void addAll(Element... elements) {
		_nativeList().addAll(Arrays.asList(elements));
	}
}