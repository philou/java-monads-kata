package monads;

import static org.junit.Assert.*;
import org.junit.Test;

public class StackTest {
	
	private static final Object A = new Object();
	private static final Object B = new Object();

	private Stack stack = new Stack();

	@Test public void
	starts_empty() {
		assertTrue(stack.empty());
	}
	
	@Test(expected = Exception.class) public void
	forbids_pop_when_empty() {
		stack.pop();
	}
	
	@Test public void
	pushes_and_pops_an_objects() {		
		stack.push(A);
		
		assertEquals(A, stack.pop());
		assertTrue(stack.empty());
	}
	
	@Test public void
	pops_objects_in_reverse_push_order() {
		stack.push(A);
		stack.push(B);
		
		assertEquals(B, stack.pop());
		assertEquals(A, stack.pop());
		assertTrue(stack.empty());
	}
}
