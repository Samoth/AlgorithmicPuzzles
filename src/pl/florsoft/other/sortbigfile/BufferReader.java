package pl.florsoft.other.sortbigfile;

public interface BufferReader<T> extends Reader<T> {

    void discard();

}