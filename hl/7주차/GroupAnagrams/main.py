class Solution:
    def groupAnagrams(self, strs: list[str]) -> list[list[str]]:

        dictt = dict()
        for s in strs:
            tmp = ''.join(sorted(s))
            if tmp not in dictt:
                dictt[tmp] = []
            dictt[tmp].append(s)

        answer = []
        for key in dictt:
            answer.append(dictt[key])
        
        return answer

strs = ["eat","tea","tan","ate","nat","bat"]
s = Solution()
s.groupAnagrams()