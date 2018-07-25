package pl.florsoft.other.sortbigfile.testimpl;

import pl.florsoft.other.sortbigfile.BufferReader;
import pl.florsoft.other.sortbigfile.BufferWriter;
import pl.florsoft.other.sortbigfile.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestBufferWriter implements BufferWriter<Long> {

    private Path filePath;
    private ByteArrayOutputStream output;
    private int numberCount;

    public TestBufferWriter() {
        try {
            this.filePath = Files.createTempFile("sorted-file-", ".long");
            this.output = new ByteArrayOutputStream();
        } catch (IOException e) {
            throw new RuntimeException("Error in TestBufferWriter(): " + e.getLocalizedMessage());
        }
    }

    @Override
    public BufferReader<Long> reopenAsReader() {
        try {
            Files.write(filePath, output.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Error in reopenAsReader(): " + e.getLocalizedMessage());
        }
        return new TestBufferReader(filePath, numberCount);
    }

    @Override
    public void write(Long val) {
        try {
            output.write(ByteUtils.longToBytes(val));
            numberCount++;
        } catch (IOException e) {
            throw new RuntimeException("Error in write(): " + e.getLocalizedMessage());
        }
    }
}
