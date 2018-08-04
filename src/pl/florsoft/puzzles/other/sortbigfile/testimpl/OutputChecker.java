package pl.florsoft.puzzles.other.sortbigfile.testimpl;

import pl.florsoft.puzzles.other.sortbigfile.Writer;

public class OutputChecker implements Writer<Long> {

    private Long prevVal = Long.MIN_VALUE;

    @Override
    public void write(Long val) {
//        System.out.println(">> " + val);
        if (val < prevVal) {
            throw new RuntimeException("Error! Incorrect sort!");
        }
        this.prevVal = val;
    }

}
