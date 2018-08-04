package pl.florsoft.puzzles.other.sortbigfile;

public interface BufferWriter<T> extends Writer<T> {

    int endWriting();

}