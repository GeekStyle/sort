package com.geekstylecn.sort.model;

import java.util.List;

public class ResponseWithDetails {
	private String code;
	private Long quickSort;
	private Long mergeSort;
	private int[] original;
	private List<Integer> sorted;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getQuickSort() {
		return quickSort;
	}
	public void setQuickSort(Long quickSort) {
		this.quickSort = quickSort;
	}
	public Long getMergeSort() {
		return mergeSort;
	}
	public void setMergeSort(Long mergeSort) {
		this.mergeSort = mergeSort;
	}
	public int[] getOriginal() {
		return original;
	}
	public void setOriginal(int[] original) {
		this.original = original;
	}
	public List<Integer> getSorted() {
		return sorted;
	}
	public void setSorted(List<Integer> sorted) {
		this.sorted = sorted;
	}
	
}
