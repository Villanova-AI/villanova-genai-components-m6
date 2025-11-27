package ai.neodatagroup.themis.chat;

public class Argument {
    private final String name;

    private final Class<?> type;

     public Argument(String name, Class<?> type) {
         this.name = name;
         this.type = type;
     }

    public void validate(Object value) {
        if (!type.isInstance(value)) {
            throw new IllegalArgumentException("Type mismatch: expected " + type.getName() + ", got " + value.getClass().getName());
        }
    }

}
