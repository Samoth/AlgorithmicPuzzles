package pl.florsoft.other.sortbigfile;

public interface BufferManager<T> {

    BufferWriter<T> getBufferWriter();

}