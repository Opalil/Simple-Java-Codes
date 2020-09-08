
import java.util.Random;

public class QuickSort {
    public static int[] a;
    public static int length;
    
    public void sort(int[] inputA){
        if(inputA == null || inputA.length == 0){
            return;
        }
        a = inputA;
        length = inputA.length;
        partition(0, length - 1);
    }
    
    public static void partition(int low, int high){
        int i = low;
        int j = high;
        int pivot = a[low + (high - low)/2];
        while(i <= j){
            while(a[i] < pivot){i++;}         
           
            while(a[j] > pivot){j--;}
            if(i <= j){
                swap(i, j);
                i++;
                j--;
            }
        }
        if(low < j) partition(low, j);
        if(i < high)partition(i, high);
        
    }
    
    public static void swap(int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
  
    public static void main(String[] args) {
       
        a = new int[100];
        Random generator = new Random();
        for(int i = 0; i < a.length; i++){
            a[i] = generator.nextInt(20000);
        }
        
        QuickSort sorter = new QuickSort();
        sorter.sort(a);
        
       for(int i: a){
           System.out.println(i + " ");
       }
        
    }
    
}
