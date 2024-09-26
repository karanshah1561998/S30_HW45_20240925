// Problem 379. Design Phone Directory
// Time Complexity :
// Space Complexity :
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
class PhoneDirectory {
    private HashSet<Integer> set;
    private Queue<Integer> q;
    int max;
    public PhoneDirectory(int maxNumbers) {
        this.q = new LinkedList<>();
        this.set = new HashSet<>();
        for(int i=0; i < maxNumbers; i++){
            set.add(i);
            q.add(i);
        }
        this.max = maxNumbers;
    }
    
    public int get() {
        if(q.isEmpty()){
            return -1;
        }
        int result = q.poll();
        set.remove(result);
        return result;
    }
    
    public boolean check(int number) {
        return set.contains(number);
    }
    
    public void release(int number) {
        if(!set.contains(number) && number < max && number >= 0){
            set.add(number);
            q.add(number);
        }
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */