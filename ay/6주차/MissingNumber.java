public class MissingNumber {
    public int missingNumber(int[] nums) {
        boolean[] exist = new boolean[nums.length + 1];
        int answer = 0;

        for (int i = 0; i < nums.length; i++) {
            exist[nums[i]] = true;
        }

        for (int i = 0; i < nums.length + 1; i++) {
            if (!exist[i]) {
                answer = i;
                break;
            }
        }

        return answer;
    }
}