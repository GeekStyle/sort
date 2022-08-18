package com.geekstylecn.sort.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.geekstylecn.sort.model.Response;
import com.geekstylecn.sort.model.ResponseWithDetails;

@RestController("/")
public class BenchmarkController {

	@GetMapping("sort/{length}")
	public @ResponseBody Response sort(@PathVariable int length) {
		
		if(length <=0 || length > 100000000) {
			Response response = new Response();
			response.setCode("400");
			response.setData("invalid input!");
			return response;	
		}
		
		int[] input = new int[length];
		for (int i = 0; i < length; i++) {
			input[i] = ThreadLocalRandom.current().nextInt(length + 1);
		}
		long startTime = System.currentTimeMillis();
		quickSort(input, 0, input.length - 1);
		long timeCost = System.currentTimeMillis() - startTime;
		System.out.println("quick sort: " + timeCost + " miliseconds");

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < length; i++) {
			list.add(input[i]);
		}
		long startTime2 = System.currentTimeMillis();
		Collections.sort(list);
		long timeCost2 = System.currentTimeMillis() - startTime2;
		System.out.println("merge sort: " + timeCost2 + " miliseconds");
		// System.out.println(list);

		// wrap response message
		Response response = new Response();
		response.setCode("200");
		response.setData("quick sort: " + timeCost + " miliseconds" + ", " + "merge sort: " + timeCost2 + " miliseconds");
		return response;
	}
	
	@GetMapping("sortwithdetails/{length}")
	public @ResponseBody Object sortwithdetails(@PathVariable int length) {
		
		if(length <=0 || length > 100000000) {
			Response response = new Response();
			response.setCode("400");
			response.setData("invalid input!");
			return response;	
		}
		
		int[] input = new int[length];
		for (int i = 0; i < length; i++) {
			input[i] = ThreadLocalRandom.current().nextInt(length + 1);
		}
		
		int[] original = new int[length];
		System.arraycopy(input, 0, original, 0, length);
		
		long startTime = System.currentTimeMillis();
		quickSort(input, 0, input.length - 1);
		long timeCost = System.currentTimeMillis() - startTime;
		System.out.println("quick sort: " + timeCost + " miliseconds");

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < length; i++) {
			list.add(input[i]);
		}
		long startTime2 = System.currentTimeMillis();
		Collections.sort(list);
		long timeCost2 = System.currentTimeMillis() - startTime2;
		System.out.println("merge sort: " + timeCost2 + " miliseconds");
		// System.out.println(list);

		// wrap response message
		ResponseWithDetails response = new ResponseWithDetails();
		response.setCode("200");
		response.setQuickSort(timeCost);
		response.setMergeSort(timeCost2);
		response.setOriginal(original);
		response.setSorted(list);
		return response;
	}

	public static void quickSort(int arr[], int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(arr, begin, end);
			quickSort(arr, begin, partitionIndex - 1);
			quickSort(arr, partitionIndex + 1, end);
		}
	}

	private static int partition(int arr[], int begin, int end) {
		int pivot = arr[end];
		int i = (begin - 1);
		for (int j = begin; j < end; j++) {
			if (arr[j] <= pivot) {
				i++;
				int swapTemp = arr[i];
				arr[i] = arr[j];
				arr[j] = swapTemp;
			}
		}
		int swapTemp = arr[i + 1];
		arr[i + 1] = arr[end];
		arr[end] = swapTemp;
		return i + 1;
	}

}
