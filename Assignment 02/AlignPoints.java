import java.util.Scanner;

public class AlignPoints {
    double [][] lineA;
    public AlignPoints(int n){
        this.lineA = new double[n*n][2];
    }
    public int calculateLine(int n,int[] x,int[] y){
        int k = 0;
        double xm,ym;
        double m;
        for (int i=0;i<n;i++){
            for(int j = i+1;j<n;j++){
                if(y[i]==y[j]) {
                    xm = (double)(x[i] + x[j]) / 2;
                    this.lineA[k][0] = xm;
                    this.lineA[k][1] = xm;
                }
                else {
                    m = -((double)(x[j] - x[i]) /(y[j] - y[i]));
                    if (m == -0.0)
                        m = 0;
                    this.lineA[k][0] = m;
                   // System.out.println(this.lineA[k][0]);
                    xm = (double)(x[i] + x[j]) / 2;
                    ym = (double)(y[i] + y[j]) / 2;
                    this.lineA[k][1] = ym - (this.lineA[k][0] * xm);
                }
                k++;
            }
        }
        return k;
    }

    public double[][] MergeSort(double[][] arr2){
     //   for(int i=0;i<arr2.length;i++){
       //     System.out.println(arr2[i][0]+"\t"+arr2[i][1]);
       // }
        double[][] A,B;
        int len = arr2.length;
        if(len==1){
            return arr2;
        }
        int middle = (len)/2;
        A = new double[middle][2];
        B = new double[len-middle][2];
        // Create subarray which stores elements from start to middle.
        for (int i=0;i<A.length;i++){
            A[i][0] = arr2[i][0];
            A[i][1] = arr2[i][1];
        }
        // Create subarray which stores elements from middle to end.
        for (int i =0;i<B.length;i++){
            B[i][0] = arr2[middle+i][0];
            B[i][1] = arr2[middle+i][1];
        }
        // Recursively call merge sort with sub-arrays.
        MergeSort(A);
        MergeSort(B);
        // Call the merge function to merge the sub-arrays
        this.merge(A,B,arr2);
        return arr2;
    }

    public void merge(double[][] As,double[][] Bs,double[][] arr){
        int AsLength = As.length;
        int BsLength = Bs.length;
        int i=0,j=0,k=0;
        // Check whether As has the lower element or Bs.
            while (i < AsLength && j < BsLength) {
                if (As[i][0] < Bs[j][0]) {
                    arr[k][0] = As[i][0];
                    arr[k][1] = As[i][1];
                    i++;
                } else if(As[i][0] == Bs[j][0]) {
                    if(As[i][1] < Bs[j][1]){
                        arr[k][0] = As[i][0];
                        arr[k][1] = As[i][1];
                        i++;
                    }
                    else {
                        arr[k][0] = Bs[j][0];
                        arr[k][1] = Bs[j][1];
                        j++;
                    }
                }
                else {
                    arr[k][0] = Bs[j][0];
                    arr[k][1] = Bs[j][1];
                    j++;
                }
                k++;
            }

        // Insert the remaining elements into the array.
        while(i<AsLength){
            arr[k][0]= As[i][0];
            arr[k][1] = As[i][1];
            i++;
            k++;
        }
        while (j<BsLength){
            arr[k][0]=Bs[j][0];
            arr[k][1] = Bs[j][1];
            j++;
            k++;
        }
    }

    public double[][] rearrange(double[][] line,int k){
        for(int i=0;i<k;i++){
            line[i][0] = this.lineA[i][0];
            line[i][1] = this.lineA[i][1];
           // System.out.println(line[i][0]);
        }
        return line;
    }

    public void findMaxCount(double[][] arr,int k){
        int count=1,max_count=1;
        if(k>1)
        {
            for (int i = 0; i < k-1; i++) {
                if (arr[i][0]==arr[i+1][0] && arr[i][1]==arr[i+1][1])
                    count++;
                else if(arr[i][0]!=arr[i+1][0] || arr[i][1]!=arr[i+1][1])
                    if(count>max_count) {
                        max_count = count;
                        count = 1;
                      // System.out.println(arr[i][0]+"\t"+arr[i][1]+"\t"+max_count+"\t"+i);
                    }
                else
                    count=1;
            }
        }
        System.out.println(max_count);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] x,y;
        int k;
        double[][] line;
        x = new int[n];
        y = new int[n];
        AlignPoints ap = new AlignPoints(n);
        for (int i=0;i<n;i++){
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
            sc.nextLine();
        }
        k = ap.calculateLine(n,x,y);
        //System.out.println(k);
        line = new double[k][2];
        line = ap.rearrange(line,k);
        // for(int i=0;i<k;i++){
          // System.out.println(line[i][0]+"\t"+line[i][1]);
         // }
            line = ap.MergeSort(line);
            ap.findMaxCount(line, k);
       //System.out.println(xline.length);
  // for(int i=10000;i<20000;i++){
    //        System.out.println(line[i][0]+"\t"+line[i][1]);
      //  }
    }

}
