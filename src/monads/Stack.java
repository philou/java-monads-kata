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
			return push(stack, value);
		}
	}

	static class Pop implements Function<Object> {
		public StackAndValue<Object> eval(Stack stack) {
			return pop(stack);
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
	
	public static StackAndValue<Void> push(Stack self, Object a) {
		return mFail(new Stack(self, a));
	}

	public static StackAndValue<Object> pop(Stack self) {
		return mReturn(self.previous, self.state);
	}
}
