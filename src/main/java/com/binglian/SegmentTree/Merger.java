package com.binglian.SegmentTree;

/**
 * 融合器,两个相加
 * @author binglian
 *
 */
public interface Merger<E> {
	
	E merge(E a,E b);

	
}
