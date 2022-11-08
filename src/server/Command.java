package server;

import java.util.ArrayList;
import java.util.List;

public class Command {
    long time;
    byte length;
    List<Byte> commands;
    String word;
    Command (String word) {
        time = System.currentTimeMillis();
        String[] splitedWord = word.split(" ");
        for(int i = 0; i<splitedWord.length; i++)
            System.out.println(splitedWord[i]);
        length = Byte.parseByte(splitedWord[0]);
        assert length == splitedWord.length-1;
        commands = new ArrayList<>(length);
        for (int i = 1; i<splitedWord.length; i++) {
            commands.add(Byte.parseByte(splitedWord[i]));
        }
        this.word = word;
    }

    @Override
    public String toString() {
        return "Command{" +
                "duration=" + (System.currentTimeMillis() - time) +
                ", length=" + length +
                ", word='" + word + '\'' +
                '}';
    }
}
