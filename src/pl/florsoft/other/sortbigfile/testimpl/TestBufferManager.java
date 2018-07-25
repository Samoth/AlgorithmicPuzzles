package pl.florsoft.other.sortbigfile.testimpl;

import pl.florsoft.other.sortbigfile.BufferManager;
import pl.florsoft.other.sortbigfile.BufferWriter;

public class TestBufferManager implements BufferManager<Long> {

    @Override
    public BufferWriter<Long> getBufferWriter() {
        return new TestBufferWriter();
    }

}
