package pl.florsoft.other.sortbigfile.testimpl;

import pl.florsoft.other.sortbigfile.Reader;

import java.util.Random;

public class RandomLongGenerator implements Reader<Long> {

    private Random random = new Random();
    private int numbersToGenerate;
    private int generatedNumbers = 0;

    public RandomLongGenerator(int numbersToGenerate) {
        this.numbersToGenerate = numbersToGenerate;
    }


    @Override
    public Long read() {
        if (generatedNumbers >= numbersToGenerate) {
            return null;
        }
        generatedNumbers++;
        return random.nextLong();
    }

    @Override
    public void restart() {
        generatedNumbers = 0;
    }

}
