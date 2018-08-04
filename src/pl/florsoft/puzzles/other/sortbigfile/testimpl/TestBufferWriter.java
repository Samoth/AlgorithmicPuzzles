package pl.florsoft.puzzles.other.sortbigfile.testimpl;

import pl.florsoft.puzzles.other.sortbigfile.BufferWriter;
import pl.florsoft.puzzles.other.sortbigfile.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestBufferWriter implements BufferWriter<Long> {

    private Path filePath;
    private ByteArrayOutputStream output;
    private int fileNumber;

    public TestBufferWriter(String fileName, int fileNumber) {
        this.fileNumber = fileNumber;
        try {
            File baseDir = new File(System.getProperty("java.io.tmpdir"));
            File file = new File(baseDir, fileName);
            file.deleteOnExit();
            this.filePath = file.toPath();
            this.output = new ByteArrayOutputStream();
        } catch (Exception e) {
            throw new RuntimeException("Error in TestBufferWriter(): " + e.getLocalizedMessage());
        }
    }

    @Override
    public void write(Long val) {
        try {
            output.write(ByteUtils.longToBytes(val));
        } catch (IOException e) {
            throw new RuntimeException("Error in write(): " + e.getLocalizedMessage());
        }
    }

    @Override
    public int endWriting() {
        try {
            Files.write(filePath, output.toByteArray());
            return fileNumber;
        } catch (IOException e) {
            throw new RuntimeException("Error in reopenAsReader(): " + e.getLocalizedMessage());
        }
    }

}
