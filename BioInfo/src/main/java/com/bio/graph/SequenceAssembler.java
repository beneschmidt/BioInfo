package com.bio.graph;

public interface SequenceAssembler<T extends Graph<?,?>> {

	T assembleGraph(T g);
}
