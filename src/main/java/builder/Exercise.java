package builder;

import java.util.ArrayList;
import java.util.List;

public class Exercise {
    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("Person").addField("name", "String").addField("age", "int");
        System.out.println(cb);
    }
}

class CodeBuilder {
    private Code code;
    public CodeBuilder(String className) {
        code = new Code(className);
    }

    public CodeBuilder addField(String name, String type) {
        code.addField(new Field(name, type));
        return this;
    }

    @Override
    public String toString() {
        return code.toString();
    }
}

class Code {
    String className;
    List<Field> fields = new ArrayList<Field>();

    public Code(String className) {
        this.className = className;
    }

    public void addField(Field field){
        fields.add(field);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("public class %s{\n", className));

        for(Field field:fields){
            stringBuilder.append(String.format("  %s\n", field));
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}

class Field {
    private String type;
    private String name;

    public Field(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s %s;", type, name);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
