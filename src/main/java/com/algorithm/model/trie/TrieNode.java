package com.algorithm.model.trie;

/**
 * 前缀树节点
 */
public class TrieNode {
    /**
     * 通过这个节点的字符串个数
     */
    public int pass;
    /**
     * 以当前节点为结尾的字符串个数
     */
    public int end;

    /**
     * 存在通向的节点
     * 如果字符种类比较多，可以用hash表的方式
     * Map<Character,TrieNode>
     */
    public TrieNode[] nexts;

    public TrieNode(){
        this.pass=0;
        this.end=0;
        // nexts[0]!=null 有走向'a'的路
        nexts=new TrieNode[26];
    }
}
