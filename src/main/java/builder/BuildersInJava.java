package builder;

public class BuildersInJava {

    public static void main(String[] args) {
        String hello = "Hello";
        System.out.println("<p>" +  hello + "</p>");

        String[] words = {"Hello", "World"};
        StringBuilder builder = new StringBuilder();
        builder.append("<ul>\n");

        for(String word:words){
            builder.append(String.format("  <li>%s</li>\n", word));
        }

        builder.append("</ul>\n");

        System.out.println(builder);
    }
}
