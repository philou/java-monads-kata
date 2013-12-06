package monads;

import static monads.Pair.*;

public final class Stack {

	static interface Function {
		Pair eval(Stack stack);
	}

	static class Push implements Function {
		private Object value;
	
		public Push(Object value) {
			this.value = value;
		}
	
		public Pair eval(Stack stack) {
			return push(stack, value);
		}
	}

	static class Pop implements Function {
		public Pair eval(Stack stack) {
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
	
	public static Pair push(Stack self, Object a) {
		return pair(new Stack(self, a));
	}

	public static Pair pop(Stack self) {
		return pair(self.previous, self.state);
	}
}
