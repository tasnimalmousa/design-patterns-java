package builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Builder {

    public static void main(String[] args) {
        HtmlBuilder builder = new HtmlBuilder("ul");
//        builder.addChild("li", "hello");
//        builder.addChild("li", "world");

        //use fluent builder
        builder
                .addChild("li", "hello")
                .addChild("li", "world");


        System.out.println(builder);
    }
}

class HtmlElement{
    public String name, text;
    public List<HtmlElement> elements = new ArrayList<>();
    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public HtmlElement() {
    }


    private String toStringImp(int indent) {
        StringBuilder stringBuilder = new StringBuilder();

        String i = String.join("", Collections.nCopies(indent * indentSize, " "));
        stringBuilder.append(String.format("%s<%s>%s",i, name, newLine));

        if(text != newLine && !text.isEmpty()){
            stringBuilder.append(String.join("", Collections.nCopies((indent + 1) * indentSize, " ")))
                    .append(text)
                    .append(newLine);
        }

        for (HtmlElement element : elements){
            stringBuilder.append(element.toStringImp(indent + 1));
        }

        stringBuilder.append(String.format("%s</%s>%s",i, name, newLine));

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return toStringImp(0);
    }
}

class HtmlBuilder{
    public String rootName;
    public HtmlElement root = new HtmlElement();

    public HtmlBuilder(String rootName) {
        this.root.name = rootName;
        this.rootName = rootName;
    }

//    public void addChild(String childName, String childText){
//        HtmlElement child = new HtmlElement(childName, childText);
//        this.root.elements.add(child);
//    }

    //Fluent Builder
    public HtmlBuilder addChild(String childName, String childText){
        HtmlElement child = new HtmlElement(childName, childText);
        this.root.elements.add(child);
        return this;
    }

    public void clear(){
        this.root = new HtmlElement();
        this.root.name = rootName;
    }

    @Override
    public String toString() {
        return this.root.toString();
    }
}
