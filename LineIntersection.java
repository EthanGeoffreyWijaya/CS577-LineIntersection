import java.util.Scanner;
import java.util.ArrayList;

public class LineIntersection {

	public static void normalSort(ArrayList<Integer> topList, ArrayList<Integer> bottomList, int startIndex,
			int endIndex) {
		if (startIndex >= endIndex) {
			return;
		}
		int start1 = startIndex;
		int end1 = startIndex + (endIndex - startIndex) / 2;
		int start2 = startIndex + (endIndex - startIndex) / 2 + 1;
		int end2 = endIndex;
		// System.out.println(start1 + " " + end1 + "|" + start2 + " " + end2);

		normalSort(topList, bottomList, start1, end1);
		normalSort(topList, bottomList, start2, end2);
		mergeSort(topList, bottomList, start1, end1, start2, end2);

	}

	public static void mergeSort(ArrayList<Integer> topList, ArrayList<Integer> bottomList, int startIndex1,
			int endIndex1, int startIndex2, int endIndex2) {
		ArrayList<Integer> full = new ArrayList<Integer>();
		ArrayList<Integer> fullTop = new ArrayList<Integer>();
		int curr1 = startIndex1;

		while (curr1 <= endIndex1 || startIndex2 <= endIndex2) {
			
			if (startIndex2 <= endIndex2 && curr1 <= endIndex1) {
				if (topList.get(startIndex2) < topList.get(curr1)) {
					full.add(bottomList.get(startIndex2));
					fullTop.add(topList.get(startIndex2++));
				} else {
					full.add(bottomList.get(curr1));
					fullTop.add(topList.get(curr1++));
				}
			} else if (startIndex2 <= endIndex2 && curr1 > endIndex1) {
				full.add(bottomList.get(startIndex2));
				fullTop.add(topList.get(startIndex2++));
			} else if (startIndex2 > endIndex2 && curr1 <= endIndex1) {
				full.add(bottomList.get(curr1));
				fullTop.add(topList.get(curr1++));
			}
			// System.out.println(full);
		}
		// System.out.println(full);
		for (int i = 0; i < full.size(); i++) {
			bottomList.set(i + startIndex1, full.get(i));
			topList.set(i + startIndex1, fullTop.get(i));
		}
	}
	
	public static long countSort(ArrayList<Integer> list, int startIndex, int endIndex) {
		long inversions = 0;
		if (startIndex >= endIndex) {
			return 0;
		}
		int start1 = startIndex;
		int end1 = startIndex + (endIndex - startIndex)/ 2;
		int start2 = startIndex + (endIndex - startIndex)/ 2 + 1;
		int end2 = endIndex;
		//System.out.println(start1 + " " + end1 + "|" + start2 + " " + end2);
		
		inversions += countSort(list, start1, end1);
		inversions += countSort(list, start2, end2);
		inversions += mergeCount(list, start1, end1, start2, end2);
		
		return inversions;
	}
	
	public static long mergeCount(ArrayList<Integer> list, int startIndex1, int endIndex1, int startIndex2, int endIndex2) {
		long inversions = 0;
		ArrayList<Integer> full = new ArrayList<Integer>();
		int curr1 = startIndex1;
		
		while (curr1 <= endIndex1 || startIndex2 <= endIndex2) {
			
			if (startIndex2 <= endIndex2 && curr1 <= endIndex1) {
				if (list.get(startIndex2) < list.get(curr1)) {
					full.add(list.get(startIndex2++));
					inversions += endIndex1 - curr1 + 1;
				} else {
					full.add(list.get(curr1++));
				}
			} else if (startIndex2 <= endIndex2 && curr1 > endIndex1) {
				full.add(list.get(startIndex2++));
			} else if (startIndex2 > endIndex2 && curr1 <= endIndex1) {
				full.add(list.get(curr1++));
			}
			//System.out.println(full);
		}
		//System.out.println(full);
		for (int i = 0; i < full.size(); i++) {
			list.set(i + startIndex1, full.get(i));
		}
		
		return inversions;
	}

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		int instances = scnr.nextInt();
		ArrayList<ArrayList<ArrayList<Integer>>> lineLists = new ArrayList<ArrayList<ArrayList<Integer>>>();

		for (int i = 0; i < instances; i++) {
			lineLists.add(new ArrayList<ArrayList<Integer>>());
			int points = scnr.nextInt();
			ArrayList<Integer> pointList = new ArrayList<Integer>();
			for (int j = 0; j < points; j++) {
				pointList.add(scnr.nextInt());
			}
			lineLists.get(i).add(new ArrayList<Integer>(pointList));
			// System.out.println(pointList);
			pointList.clear();
			for (int j = 0; j < points; j++) {
				pointList.add(scnr.nextInt());
			}
			// System.out.println(pointList);
			lineLists.get(i).add(pointList);
		}
		scnr.close();
		// System.out.println(lineLists);

		for (ArrayList<ArrayList<Integer>> x : lineLists) {
			normalSort(x.get(0), x.get(1), 0, x.get(0).size() - 1);
			System.out.println(countSort(x.get(1), 0, x.get(1).size() - 1));
		}
	}

}
