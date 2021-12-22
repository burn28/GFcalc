import java.util.*;

public class GFcalculator {

    static int n;
    static String operation, a,b;
    static String[] arrayA,arrayB;

    public static void menu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("GALOIS FIELD(2^N) CALCULATOR");
        System.out.println("ENTER THE DESIRE N");
        n = scan.nextInt();
        System.out.println("ENTER A VALUE IN BINARY UNTIL X^0");
        a = scan.next();
        arrayA = a.split("(?!^)");
        System.out.println("ENTER OPERATION(+, -, x");
        operation = scan.next();
        System.out.println("ENTER B VALUE IN BINARY UNTIL X^0");
        b = scan.next();
        arrayB = b.split("(?!^)");
        System.out.println(n);
        System.out.println(Arrays.toString(arrayA));
        System.out.println(operation);
        System.out.println(Arrays.toString(arrayB));
    }

    public static void addSub(){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < a.length(); i++) {
            sb.append((a.charAt(i) ^ b.charAt(i)));
        }
        String result = sb.toString();
//        System.out.println(result);
//        System.out.println(binToPoly(result.split("(?!^)")));
        ArrayList test=binToPoly(result.split("(?!^)"));
        System.out.println("="+test);
    }

    public static ArrayList<Integer> binToPoly(String[] array){
        ArrayList<Integer> poly = new ArrayList<>();
//        int[] poly = new int[array.length];
        int greatest = array.length-1;
        for(int i = 0; i < array.length; i++){
            if(array[i].equals("1")){
//                poly[i]=(greatest);
                poly.add(greatest);
            }
            greatest = greatest-1;
        }
        return poly;
    }

    public static void multiply(){
        ArrayList<Integer> a = binToPoly(arrayA);
        ArrayList<Integer> b = binToPoly(arrayB);
        ArrayList<Integer> result = new ArrayList<>();
        Iterator<Integer> itr;
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                result.add(a.get(i)+b.get(j));
            }
        }
//        System.out.println(result);

        ArrayList<Integer> latest = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            for (int j = i+1; j < result.size()-1; j++) {
                if(result.get(i) == result.get(j)){
                    latest.add(result.get(i));
                    latest.add(result.get(j));
                }
            }
        }
        result.removeAll(latest);

        if(result.get(0)<n){
            System.out.println(result);
//            polyToDec(result);
        }else{
            System.out.println(result);
//            System.out.println(latest);
            irreducible(result);
        }
    }

    public static void irreducible(ArrayList<Integer> results){
        ArrayList<Integer> one = new ArrayList<>(){{
            add(1);
            add(0);
        }};
        ArrayList<Integer> two = new ArrayList<>(){{
            add(2);
            add(1);
            add(0);
        }};
        ArrayList<Integer> three = new ArrayList<>(){{
            add(3);
            add(1);
            add(0);
        }};
        ArrayList<Integer> four = new ArrayList<>(){{
            add(4);
            add(1);
            add(0);
        }};
        ArrayList<Integer> five = new ArrayList<>(){{
            add(5);
            add(2);
            add(1);
        }};
        ArrayList<Integer> six = new ArrayList<>(){{
            add(6);
            add(1);
            add(0);
        }};
        ArrayList<Integer> seven = new ArrayList<>(){{
            add(7);
            add(1);
            add(0);
        }};
        ArrayList<Integer> eight = new ArrayList<>(){{
            add(8);
            add(4);
            add(3);
            add(1);
            add(0);
        }};
        ArrayList<Integer> dividend = new ArrayList<>();

        ArrayList<Integer> divisor = new ArrayList<>();
//        ArrayList<Integer> quotient = new ArrayList<>();
        ArrayList<Integer> reminder = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        int quotient;

        switch (n){
            case 1: divisor=one;
                break;
            case 2: divisor=two;
                break;
            case 3: divisor=three;
                break;
            case 4: divisor=four;
                break;
            case 5: divisor=five;
                break;
            case 6: divisor=six;
                break;
            case 7: divisor=seven;
                break;
            case 8: divisor=eight;
                break;
        }


        dividend = results;
        do{

            quotient = dividend.get(0)-divisor.get(0);
            System.out.println("q"+quotient);
            for(int i=0; i<divisor.size(); i++){
                temp.add(divisor.get(i)+quotient);
            }
            System.out.println("T "+temp);
            dividend.addAll(temp);
            temp.clear();
            reminder.clear();
            reminder.addAll(dividend);

            ArrayList<Integer> check = new ArrayList<>();
            for(int i=0; i < dividend.size(); i++){
                for (int j = i+1; j < dividend.size(); j++) {
                    if(dividend.get(i) == dividend.get(j)){
                        check.add(dividend.get(i));
                    }
                }
            }
            reminder.removeAll(check);
            reminder.sort(Collections.reverseOrder());
            if(reminder.get(0)>=n){
                dividend.clear();
                dividend.addAll(reminder);
                System.out.println("Dividend"+dividend);
            }
        }while (reminder.get(0) >= n);
        System.out.println(reminder);
    }

    public static Integer binToDec(String[] bin){
//        ArrayList<Integer> bin = new ArrayList<>();
//        int greatest = poly.get(0);
//        bin.add(1);
//        //poly to bin
//        for (int i = 1; i < poly.get(0)+1 ; i++) {
//            greatest--;
//            if(poly.get(i) == greatest){
//                bin.add(1);
//            }else {
//                bin.add(0);
//            }
//
//        }
//        System.out.println(bin);

        //bin to dec
        StringBuilder sb = new StringBuilder();
        for (String binary : bin) {
            sb.append(binary);
        }
        int decimal = Integer.parseInt(sb.toString(),2);
        System.out.println(decimal);
        return decimal;
    }

    public static void inverse(){
        ArrayList<Integer> dividend = new ArrayList<>();
        ArrayList<Integer> divisor = new ArrayList<>();
        ArrayList<Integer> quotient = new ArrayList<>();
        ArrayList<Integer> remainder = new ArrayList<>();

        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        x.add(1);
        x.add(0);
        y.add(0);
        y.add(1);

        dividend.add(binToDec(arrayA));
        divisor.add(binToDec(arrayB));
        int i =0;
        do {

            quotient.add(dividend.get(i)/divisor.get(i));
            remainder.add(dividend.get(i)%divisor.get(i));

            dividend.add(divisor.get(i));
            divisor.add(remainder.get(i));
            i++;

        }while (remainder.get(remainder.size()-1) > 1);



        for (int j = 0; j < quotient.size(); j++) {
            x.add(x.get(j)-x.get(j+1)*quotient.get(j));
            y.add(y.get(j)-y.get(j+1)*quotient.get(j));
        }


        System.out.println(dividend);
        System.out.println(divisor);
        System.out.println(quotient);
        System.out.println(remainder);
        System.out.println(x);
        System.out.println(y);
        System.out.println("x = "+x.get(x.size()-1));
        System.out.println("y = "+y.get(y.size()-1));

    }


    public static void main(String[] args) {
        System.out.print("hai ");
        menu();
//        addSub();
//        multiply();
        inverse();

    }

}
