/**
 완전 탐색 (모두 비교)
 */
import Foundation

extension String {
	func substring(start: Int, end: Int)->String{
		let start = self.index(self.startIndex, offsetBy: start)
		let end = self.index(self.startIndex, offsetBy: end)
		return String(self[start..<end])
	}
}

var case1 = [
	"WBWBWBWB",
	"BWBWBWBW",
	"WBWBWBWB",
	"BWBWBWBW",
	"WBWBWBWB",
	"BWBWBWBW",
	"WBWBWBWB",
	"BWBWBWBW",
]
var case2 = [
	"BWBWBWBW",
	"WBWBWBWB",
	"BWBWBWBW",
	"WBWBWBWB",
	"BWBWBWBW",
	"WBWBWBWB",
	"BWBWBWBW",
	"WBWBWBWB",
]

func getCount(_ sy:Int, _ sx:Int, _ board:[String])->Int {
	
	// 경우의수1 화블
	var count1 = 0
	
	// 경우의수2 블화
	var count2 = 0
	
	for i in 0...7 {
		for j in 0...7 {
			var s1 = board[sy+i].substring(start: sx+j, end: sx+j+1)
			var s2 = case1[i].substring(start: j, end: j+1)
			var s3 = case2[i].substring(start: j, end: j+1)
			
			if s1 != s2 {
				count1 += 1
			}
			if s1 != s3 {
				count2 += 1
			}
		}
	}
	return min(count1, count2)
}

// 입력 받기
var temp = readLine()!.split(separator: " ")
var H = Int(temp[0])!
var W = Int(temp[1])!
var board = [String]()
for _ in 1...H {
	board.append(readLine()!)
}

var minCount = 99999

// 시간 복잡도: 50*50
// 각 시작 좌표마다 개수 카운트
for sy in 0...H-8 {
	for sx in 0...W-8 {
		// 시간 복잡도: 8*8
		var count = getCount(sy, sx, board)
		minCount = min(minCount, count)				// 최솟값 갱신
	}
}

print(minCount)
//print(50*50*8*8)	// O(160000)
