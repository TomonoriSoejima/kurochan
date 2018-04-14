import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Kurochan {
    public static void main(String[] args) throws InterruptedException {

        String fileName = "/Users/surfer/elastic/src/kurochan/src/main/resources/input.txt";

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(Kurochan::Tokenize);

        } catch (IOException e) {
            e.printStackTrace();
        }


        Thread.sleep(6000);
    }

    private static void Tokenize(String input_line) {

        Tokenizer.Builder builder = Tokenizer.builder();
        builder.mode(Tokenizer.Mode.SEARCH);
        Tokenizer tokenizer = builder.build();

        String[]tokens = input_line.split("=>|\\,");

        for (String word : tokens) {
            String trimmed_word = word.trim();

            List<Token> tokenized_list = tokenizer.tokenize(trimmed_word);
            if (tokenized_list.size() > 1) {

                System.out.println(trimmed_word + " needs to be in user dictionary as");

                for (Token token : tokenized_list) {
                    System.out.print(token.getSurfaceForm() + " ");
                }
                System.out.println();
            }

        }
    }
}