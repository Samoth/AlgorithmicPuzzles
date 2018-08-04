package pl.florsoft.puzzles.other.sortbigfile;

public interface BufferReader<T> extends Reader<T> {

    void discard();

}