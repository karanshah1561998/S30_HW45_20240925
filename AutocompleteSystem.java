// Problem 642. Design Search Autocomplete System
// Time Complexity :
// Space Complexity :
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
class AutocompleteSystem {
    private HashMap<String, Integer> map;
    private StringBuilder sb;
    private TrieNode root;

    class TrieNode{
        List<String> li;
        TrieNode[] children;
        public TrieNode(){
            this.children = new TrieNode[256];
            this.li = new ArrayList<>();
        }
    }

    private void insert(String word){
        int n = word.length();
        TrieNode curr = root;
        for(int i=0; i < n; i++){
            char c = word.charAt(i);
            if(curr.children[c - ' '] == null){
                curr.children[c - ' '] = new TrieNode();
            }
            curr = curr.children[c - ' '];
            curr.li.add(word);
        }
    }

    private List<String> search(String prefix){
        int n = prefix.length();
        TrieNode curr = root;
        for(int i=0; i < n; i++){
            char c = prefix.charAt(i);
            if(curr.children[c - ' '] == null){
                return new ArrayList<>();
            }
            curr = curr.children[c - ' '];
        }
        return curr.li;
    }
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.sb = new StringBuilder();
        this.root = new TrieNode();
        for(int i=0; i< sentences.length; i++){
            String curr = sentences[i];
            int cnt = times[i];
            map.put(curr, cnt);
            insert(curr);
        }
    }
    
    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        if(c == '#'){
            // put search term in cache
            String searchTerm = sb.toString();
            if(!map.containsKey(searchTerm)){
                insert(searchTerm);
            }
            map.put(searchTerm, map.getOrDefault(searchTerm, 0) + 1);
            // reset sb
            sb = new StringBuilder();
            return result;
        }
        // normal alphabet
        sb.append(c);
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
            // counts
            int cnta = map.get(a);
            int cntb = map.get(b);
            if(cnta == cntb){
                // priority based on lexigraphic order
                return b.compareTo(a);
            }
            return cnta - cntb;
        });
        // search which words are starting with curr search term
        String searchTerm = sb.toString();
        List<String> li = search(searchTerm);
        for(String key: li){
            if(key.startsWith(searchTerm)){
                pq.add(key);
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }
        while(!pq.isEmpty()){
            result.add(0, pq.poll());
        }
        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */