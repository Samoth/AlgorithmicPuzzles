package pl.florsoft.puzzles.other.sortbigfile;

public interface BufferWriter<T> extends Writer<T> {

    BufferReader<T> reopenAsReader();

}