package prototype.serialization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

//deep copy
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Foo implements Serializable {
    private int stuff;
    private String whatever;
}

class Demo{
    public static void main(String[] args) {
        Foo foo1 = new Foo(42, "life");

        Foo foo2 = SerializationUtils.roundtrip(foo1);//serializes and deserialize (new copy)
        foo2.setWhatever("xyz");

        System.out.println(foo1);
        System.out.println(foo2);
    }
}