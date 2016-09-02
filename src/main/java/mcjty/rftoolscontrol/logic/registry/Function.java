package mcjty.rftoolscontrol.logic.registry;

public class Function {

    private final String id;
    private final String name;
    private final FunctionRunnable functionRunnable;
    private final ParameterType type;

    private Function(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.functionRunnable = builder.functionRunnable;
        this.type = builder.type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public FunctionRunnable getFunctionRunnable() {
        return functionRunnable;
    }

    public ParameterType getType() {
        return type;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private FunctionRunnable functionRunnable;
        private ParameterType type;
        private String name;

        public Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder runnable(FunctionRunnable runnable) {
            this.functionRunnable = runnable;
            return this;
        }

        public Builder type(ParameterType type) {
            this.type = type;
            return this;
        }

        public Function build() {
            return new Function(this);
        }
    }
}