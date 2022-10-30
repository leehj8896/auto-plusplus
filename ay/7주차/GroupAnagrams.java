import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> answer = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] charArr = str.toCharArray();
            Arrays.sort(charArr);
            String result = new String(charArr);

            List<String> list = map.getOrDefault(result, new ArrayList<String>());
            list.add(str);
            map.put(result, list);
        }

        for (String key : map.keySet()) {
            answer.add(map.get(key));
        }

        return answer;
    }
}