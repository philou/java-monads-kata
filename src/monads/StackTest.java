package monads;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StackTest {
	
	private static final Object A = new Object();
	private static final Object B = new Object();

	private Stack stack = Stack.empty();

	@Test public void
	starts_empty() {
		assertEquals(Stack.empty(), stack);
	}
	
	@Test(expected = Exception.class) public void
	forbids_pop_when_empty() {
		Stack.pop(stack);
	}
	
	@Test public void
	pushes_and_pops_an_objects() {		
		stack = Stack.push(stack, A);
		
		Pair pair = Stack.pop(stack);
		assertEquals(A, pair.value);
		assertEquals(Stack.empty(), pair.stack);
	}
	
	@Test public void
	pops_objects_in_reverse_push_order() {
		stack = Stack.push(stack, A);
		stack = Stack.push(stack, B);
		
		Pair pair = Stack.pop(stack);
		assertEquals(B, pair.value);

		pair = Stack.pop(pair.stack);
		assertEquals(A, pair.value);

		assertEquals(Stack.empty(), pair.stack);
	}
}
