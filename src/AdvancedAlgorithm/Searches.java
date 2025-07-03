package AdvancedAlgorithm;

/*
Name: Nick Jeong
Title: Searches
Date: Jun 1, 2024
Description: Searches number needed
*/

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Searches {

    // Main method to test the interpolation search
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            int[] randomArray = generateRandomArray(500, 800, 999);
            Arrays.sort(randomArray);
            printArray(randomArray);

            System.out.print("\nEnter an integer to search: ");
            int searchValue = scanner.nextInt();

            if (searchValue >= 800 && searchValue <= 999) {
                searchAndPrintResults(randomArray, searchValue);
            } else {
                System.out.println("Please enter value in between 800 ~ 999!\n");
            }

            System.out.print("Do you wish for another search: 'n' to stop, other keys to continue: ");
            input = scanner.next();
        } while (!input.equals("n"));
    }

    /**
     * Generates an array of random integers within a specified range.
     *
     * @param size The size of the array to generate.
     * @param min  The minimum value (inclusive) for random integers.
     * @param max  The maximum value (inclusive) for random integers.
     * @return An array of random integers within the specified range.
     */
    private static int[] generateRandomArray(int size, int min, int max) {
        Random random = new Random();
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }

        return array;
    }

    /**
     * Prints the elements of the array, 20 elements per line.
     *
     * @param array The array to print.
     */
    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i > 0 && i % 20 == 0) {
                System.out.println();
            }
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * Searches for a value in the array and prints the search results.
     *
     * @param array The sorted array to search.
     * @param value The value to search for.
     */
    private static void searchAndPrintResults(int[] array, int value) {
        int[] bounds = interpolationSearchRange(array, value);
        if (bounds[0] == bounds[1]) {
            System.out.printf("Found at index %d\n", bounds[1]);
        } else if (bounds[0] == -1 || bounds[1] == -1) {
            System.out.println("Item not found");
        } else {
            System.out.printf("Found in Range (%d, %d)\n", bounds[0], bounds[1]);
        }
    }

    /**
     * Interpolation search method to find an occurrence of the value.
     *
     * @param arr   The array to search through, must be sorted.
     * @param value The value to search for.
     * @return The index of the value if found, otherwise -1.
     */
    public static int interpolationSearch(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high && value >= arr[low] && value <= arr[high]) {
            if (low == high) {
                if (arr[low] == value) return low;
                return -1;
            }
            int pos = low + ((value - arr[low]) * (high - low)) / (arr[high] - arr[low]);
            if (arr[pos] == value) return pos;

            if (arr[pos] < value) low = pos + 1;
            else high = pos - 1;
        }
        return -1; // Value not found
    }

    /**
     * Find the lower bound of the target value in the array.
     *
     * @param arr   The array to search through, must be sorted.
     * @param value The value to search for.
     * @param start The starting index to search from.
     * @return The index of the first occurrence of the value.
     */
    public static int findLowerBound(int[] arr, int value, int start) {
        int index = start;
        while (index > 0 && arr[index - 1] == value) {
            index--;
        }
        return index;
    }

    /**
     * Find the upper bound of the target value in the array.
     *
     * @param arr   The array to search through, must be sorted.
     * @param value The value to search for.
     * @param start The starting index to search from.
     * @return The index of the last occurrence of the value.
     */
    public static int findUpperBound(int[] arr, int value, int start) {
        int index = start;
        while (index < arr.length - 1 && arr[index + 1] == value) {
            index++;
        }
        return index;
    }

    /**
     * Interpolation search method to find the range of occurrences of the value.
     *
     * @param arr   The array to search through, must be sorted.
     * @param value The value to search for.
     * @return An array with the lower and upper bounds of the value's occurrences.
     */
    public static int[] interpolationSearchRange(int[] arr, int value) {
        int index = interpolationSearch(arr, value);
        if (index == -1) {
            return new int[]{-1, -1}; // Value not found
        }
        int lowerBound = findLowerBound(arr, value, index);
        int upperBound = findUpperBound(arr, value, index);
        return new int[]{lowerBound, upperBound};
    }
}
