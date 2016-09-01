package mcjty.rftoolscontrol.logic.registry;

import java.util.ArrayList;
import java.util.List;

public class Opcode {

    private final String id;
    private final OpcodeOutput opcodeOutput;
    private final boolean isEvent;
    private final List<ParameterDescription> parameters;

    private final int iconU;
    private final int iconV;

    private Opcode(Builder builder) {
        this.id = builder.id;
        this.opcodeOutput = builder.opcodeOutput;
        this.isEvent = builder.isEvent;
        this.parameters = new ArrayList<>(builder.parameters);
        this.iconU = builder.iconU;
        this.iconV = builder.iconV;
    }

    public String getId() {
        return id;
    }

    public OpcodeOutput getOpcodeOutput() {
        return opcodeOutput;
    }

    public boolean isEvent() {
        return isEvent;
    }

    public List<ParameterDescription> getParameters() {
        return parameters;
    }

    public int getIconU() {
        return iconU;
    }

    public int getIconV() {
        return iconV;
    }

    public ParameterDescription findParameter(String name) {
        for (ParameterDescription description : parameters) {
            if (name.equals(description.getName())) {
                return description;
            }
        }
        return null;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private OpcodeOutput opcodeOutput = OpcodeOutput.SINGLE;
        private boolean isEvent = false;
        private int iconU;
        private int iconV;
        private List<ParameterDescription> parameters = new ArrayList<>();

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder opcodeOutput(OpcodeOutput opcodeOutput) {
            this.opcodeOutput = opcodeOutput;
            return this;
        }

        public Builder isEvent(boolean isEvent) {
            this.isEvent = isEvent;
            return this;
        }

        public Builder icon(int u, int v) {
            this.iconU = u;
            this.iconV = v;
            return this;
        }

        public Builder parameter(ParameterDescription parameter) {
            parameters.add(parameter);
            return this;
        }

        public Opcode build() {
            return new Opcode(this);
        }
    }
}