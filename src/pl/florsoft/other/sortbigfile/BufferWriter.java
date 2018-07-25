package pl.florsoft.other.sortbigfile;

public interface BufferWriter<T> extends Writer<T> {

    BufferReader<T> reopenAsReader();

}