package pl.florsoft.puzzles.other.sortbigfile.testimpl;

import pl.florsoft.puzzles.other.sortbigfile.BufferReader;
import pl.florsoft.puzzles.other.sortbigfile.ByteUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public class TestBufferReader implements BufferReader<Long> {

    private File file;
    private FileInputStream fis;
    private int numberCount;

    public TestBufferReader(Path filePath, int numberCount) {
        this.numberCount = numberCount;
        file = filePath.toFile();
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error in TestBufferReader():" + e.getLocalizedMessage());
        }
    }

    @Override
    public void discard() {
        try {
            fis.close();
            file.delete();
            this.finalize();
        } catch (Throwable throwable) {
            System.out.println("Cannot finalize!");
        }
    }

    @Override
    public Long read() {
        if (--numberCount < 0) {
            discard();
            return null;
        }
        byte[] bytesArray = new byte[Long.BYTES];
        Long val = null;
        try {
            fis.read(bytesArray);
            val = ByteUtils.bytesToLong(bytesArray);
        } catch (IOException e) {
            throw new RuntimeException("Error in read():" + e.getLocalizedMessage());
        }
        return val;
    }

    @Override
    public void restart() {
        // no impl
    }

}
