public class test {
    public static void main(String[] args)
    {
        int[] arr =  {9,2,5,4,1,3,8,6,10,7};
        for(int i=0;i<arr.length;i++) System.out.print(arr[i]+" ");
        System.out.println();
        for(int i=0;i<arr.length;i++){
            int min = i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[min])min=j;
            }
            int temp = arr[min];
            arr[min]=arr[i];
            arr[i]=temp;
        }
        for(int i=0;i<arr.length;i++) System.out.print(arr[i]+" ");
    }
}
