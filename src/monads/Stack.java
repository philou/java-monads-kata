package monads;

import static monads.StackAndValue.*;

public final class Stack {

	static interface Function<T> {
		StackAndValue<T> eval(Stack stack);
	}

	static class Push implements Function<Void> {
		private Object value;
	
		public Push(Object value) {
			this.value = value;
		}
	
		public StackAndValue<Void> eval(Stack stack) {
			return mFail(new Stack(stack, value));
		}
	}

	static class Pop implements Function<Object> {
		public StackAndValue<Object> eval(Stack stack) {
			return mReturn(stack.previous, stack.state);
		}
	}

	private final Stack previous;
	private final Object state;
	
	public static Stack empty() {
		return null;
	}
	
	private Stack(Stack previous, Object state) {
		this.previous = previous;
		this.state = state;
	}

	static Push push(Object value) {
		return new Push(value);
	}

	static Pop pop() {
		return new Pop();
	}
}
