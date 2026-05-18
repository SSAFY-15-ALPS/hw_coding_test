import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long lo = 1;
        int max = -1;
        for(int time : times) {
            max = Math.max(time, max);
        }
        long hi = (long) max * n;
        
        // System.out.println(hi);
        
        long answer = hi;
        
        while(lo <= hi) {
            long mid = (lo + hi) / 2;
            long count = 0;
            for(int time : times) {
                count += (mid / time);
            }
            
            if(count >= n) {
                answer = mid;
                hi = mid -1;
            } else {
                lo = mid + 1;
            }
        }
        return answer;
    }
}