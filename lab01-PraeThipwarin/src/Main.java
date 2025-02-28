import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void sort(ArrayList<Integer> numbers) {
        int n = numbers.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (numbers.get(j) > numbers.get(j + 1)) {
                    int temp = numbers.get(j);
                    numbers.set(j, numbers.get(j + 1));
                    numbers.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Integer> nums = new ArrayList<>();

        System.out.println("Enter numbers: ");
        while (true) {
            int number = Integer.parseInt(s.nextLine());
            if(number >= 0) {
                nums.add(number);
            }else {
                break;
            }
        }

        sort(nums);
        System.out.println("-----Result-----");
        for(int i = 0; i < nums.size(); i++) {
            System.out.println(nums.get(i));
        }
    }
}