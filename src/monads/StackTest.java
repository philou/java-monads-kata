package monads;

import static org.junit.Assert.assertEquals;
import static monads.Stack.*;
import static monads.Pair.*;

import org.junit.Test;

public class StackTest {
	
	private static final Object A = new Object();
	private static final Object B = new Object();

	private Pair pair = pair(empty());

	@Test public void
	starts_empty() {
		assertEquals(empty(), pair.stack);
	}
	
	@Test(expected = Exception.class) public void
	forbids_pop_when_empty() {
		pair = pair.bind(pop());
	}
	
	
	@Test public void
	pushes_and_pops_an_objects() {	
		pair = pair.
				bind(push(A)).
				bind(pop());

		assertEquals(A, pair.value);
		assertEquals(empty(), pair.stack);
	}

	@Test public void
	pops_objects_in_reverse_push_order() {
		pair = pair.
				bind(push(A)).
				bind(push(B)).
				
				bind(pop());

		assertEquals(B, pair.value);

		pair = pair.bind(pop());
		assertEquals(A, pair.value);

		assertEquals(empty(), pair.stack);
	}

	private Stack.Push push(Object value) {
		return new Stack.Push(value);
	}
	private Stack.Pop pop() {
		return new Stack.Pop();
	}
}
