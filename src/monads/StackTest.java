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
		do_(pop());
	}
	
	
	@Test public void
	pushes_and_pops_an_objects() {	
		do_(push(A));
		do_(pop());

		assertEquals(A, pair.value);
		assertEquals(empty(), pair.stack);
	}

	@Test public void
	pops_objects_in_reverse_push_order() {
		do_(push(A));
		do_(push(B));
		
		do_(pop());
		assertEquals(B, pair.value);

		do_(pop());
		assertEquals(A, pair.value);

		assertEquals(empty(), pair.stack);
	}

	private static interface StackFunction {
		Pair eval(Stack stack);
	}

	private void do_(StackFunction operation) {
		pair = operation.eval(pair.stack);
	}

	private static class StackPush implements StackFunction {
		private Object value;

		public StackPush(Object value) {
			this.value = value;
		}

		public Pair eval(Stack stack) {
			return Stack.push(stack, value);
		}
	}
	private StackPush push(Object value) {
		return new StackPush(value);
	}
	
	private static class StackPop implements StackFunction {
		public Pair eval(Stack stack) {
			return Stack.pop(stack);
		}
	}
	private StackPop pop() {
		return new StackPop();
	}
}
