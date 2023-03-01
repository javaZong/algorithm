package com.algorithm.model.trie;

public class TrieTree {
    public TrieNode root;

    public TrieTree() {
        root = new TrieNode();
    }

    /**
     * 加入字符串
     *
     * @param word
     */
    public void insert(String word) {
        if (word == null || word.length() < 1) {
            return;
        }
        TrieNode node = root;
        // 进行过多少次插入操作
        node.pass++;
        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            int index = aChar - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end++;
    }

    /**
     * 查询这个字符串一共出现过多少次
     *
     * @param word
     * @return
     */
    public int search(String word) {
        if (word == null || word.length() < 1) {
            return 0;
        }
        TrieNode node = root;
        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            int index = aChar - 'a';
            node = node.nexts[index];
            if (node == null) {
                return 0;
            }
        }
        return node.end;
    }

    /**
     * 以word作为前缀的个数
     * @param word
     * @return
     */
    public int preSearchNum(String word) {
        if (word == null || word.length() < 1) {
            return 0;
        }
        TrieNode node = root;
        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            int index = aChar - 'a';
            node = node.nexts[index];
            if (node == null) {
                return 0;
            }
        }
        return node.pass;
    }

    public void delete(String word){
        if(word==null){
            return;
        }
        if(search(word)<1){
            return;
        }
        TrieNode node=root;
        char[] chars=word.toCharArray();
        for (char c:chars){
            int index=c-'a';
            int pass=node.nexts[index].pass;
            if(--pass==0){
                node.nexts[index]=null;
                return;
            }
            node=node.nexts[index];
        }
        node.end--;
    }
    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree();
        trieTree.insert("apple");
        trieTree.insert("axxe");
        System.out.println(trieTree.search("apple"));
    }
}
