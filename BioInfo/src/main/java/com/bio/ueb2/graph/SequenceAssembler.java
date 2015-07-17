package com.bio.ueb2.graph;

public interface SequenceAssembler<T extends Graph<?,?>> {

	T assembleGraph(T g);
}
