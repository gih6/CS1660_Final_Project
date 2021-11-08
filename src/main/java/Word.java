public class Word implements Comparable<Word>{
    int totalFrequency;
    String word;

    Word(String word, int totalFreq){
        this.totalFrequency = totalFreq;
        this.word = word;
    }

    @Override
    public int compareTo(Word o) {
        return o.totalFrequency-this.totalFrequency;
    }
    @Override
    public String toString(){return this.word + ":" + this.totalFrequency;}

}
