
public class Test2 {





    public static void main(String[] args) {
        int l = 0, r = 40;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int mid2 = (r - l + 1) / 2 + l;
            System.out.println("mid:" + mid + ", mid2: " + mid2);
            //l = mid + 1;
            r = mid - 1;


        }

    }


}
