package server;

import java.util.ArrayList;
import java.util.List;

public class Command {
    long time;
    byte length;
    List<Byte> commands;

    List<Integer> data;
    int processedData;
    String word;
    int polygons;
    Command (String word) {
        time = System.currentTimeMillis();
        String[] headerAndBody = word.split("\\|");
        parseHeader(headerAndBody[0]);
        if (headerAndBody.length == 2)
            parseBody(headerAndBody[1]);
        else assertTrue (polygons == 0);
        this.word = word;
    }

    private void parseHeader(String word) {
        String[] splitedWord = word.split(" +");
        length = Byte.parseByte(splitedWord[0]);
        assertTrue(length == splitedWord.length-1);
        commands = new ArrayList<>(length);
        polygons = 0;
        for (int i = 1; i<splitedWord.length; i++) {
            commands.add(Byte.parseByte(splitedWord[i]));
            if (Byte.parseByte(splitedWord[i]) == 100)
                polygons++;
        }
    }
    private void parseBody(String word) {
        String[] splitedWord = word.trim().split(" +");
        assertTrue (polygons*12 == splitedWord.length);
        data = new ArrayList<>(length);
        for (int i = 0; i<splitedWord.length; i++) {
            data.add(Integer.parseInt(splitedWord[i]));
        }
        processedData=0;
    }

    @Override
    public String toString() {
        return "Command{" +
                "duration=" + (System.currentTimeMillis() - time) +
                ", length=" + length +
                ", word='" + word + '\'' +
                '}';
    }

    private void assertTrue(boolean b){
        if (!b)
            throw new AssertionError();
    }
}
