package monads;

import java.util.ArrayList;

public class Stack {
	
	private ArrayList<Object> state = new ArrayList<Object>();
	
	public void push(Object a) {
		state.add(a);
	}

	public Object pop() {
		int count = state.size();
		Object result = state.get(count-1);
		state.remove(count-1);
		return result;
	}

	public boolean empty() {
		return state.isEmpty();
	}

}
