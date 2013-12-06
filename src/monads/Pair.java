package monads;

import monads.Stack.Function;

public final class Pair {
	
	public final Stack stack;
	public final Object value;

	public static Pair pair(Stack stack) {
		return pair(stack, null);
	}
	public static Pair pair(Stack stack, Object value) {
		return new Pair(stack, value);
	}
		
	public Pair(Stack stack, Object value) {
		this.stack = stack;
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stack == null) ? 0 : stack.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (stack == null) {
			if (other.stack != null)
				return false;
		} else if (!stack.equals(other.stack))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	public Pair bind(Stack.Function operation) {
		return operation.eval(this.stack);
	}
}
