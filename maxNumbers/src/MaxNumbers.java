public class MaxNumbers {
    private int maxNumber;
    private String line;

    public MaxNumbers(String line){
        this.line = line;
        maxNumber = Integer.MIN_VALUE;
    }

    public int getMaxValue(){
        return maxNumber;
    };

    private void calcMaxValue(int[] values){
        for(int i = 0; i < values.length; i++){
            if(values[i] > maxNumber)
                maxNumber = values[i];
        }
    }
}
