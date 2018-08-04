package pl.florsoft.puzzles.other.sortbigfile.testimpl;

import pl.florsoft.puzzles.other.sortbigfile.BufferReader;
import pl.florsoft.puzzles.other.sortbigfile.ByteUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestBufferReader implements BufferReader<Long> {

    private File file;
    private FileInputStream fis;

    public TestBufferReader(String fileName) {
        File baseDir = new File(System.getProperty("java.io.tmpdir"));
        file = new File(baseDir, fileName);
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
            throw new RuntimeException("Cannot finalize!");
        }
    }

    @Override
    public Long read() {
        byte[] bytesArray = new byte[Long.BYTES];
        Long val = null;
        try {
            if (fis.read(bytesArray) == -1) {
                discard();
                return null;
            }
            val = ByteUtils.bytesToLong(bytesArray);
            return val;
        } catch (IOException e) {
            throw new RuntimeException("Error in read():" + e.getLocalizedMessage());
        }
    }

}
