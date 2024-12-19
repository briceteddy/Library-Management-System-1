package Library;

import java.util.ArrayList;

public class ActivityStack {
    private ArrayList<String> stack;

    public ActivityStack() {
        stack = new ArrayList<>();
    }

    public void push(String activity) {
        stack.add(activity);
    }

    public String pop() {
        if (!stack.isEmpty()) {
            return stack.remove(stack.size() - 1);
        }
        return null;
    }

    public String peek() {
        if (!stack.isEmpty()) {
            return stack.get(stack.size() - 1);
        }
        return null;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
