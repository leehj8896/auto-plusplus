/**
 자료구조 스택
 
 열리면 push
 닫히면 pop 후 비교
 
 1. 잘못 닫은 경우 >> 여는게 없음
 2. 잘못 연 경우 >> 닫는게 없음
 */
import Foundation

extension String {
	func substring(start: Int, end: Int)->String{
		let start = self.index(self.startIndex, offsetBy: start)
		let end = self.index(self.startIndex, offsetBy: end)
		return String(self[start..<end])
	}
}

var T = Int(readLine()!)!

for _ in 1...T {
	
	var stack = [String]()
	var string = readLine()!
	var vps = true
	
	for i in 0...string.count-1 {
		var curr = string.substring(start: i, end: i+1)
		
		if curr == "(" {
			stack.append(curr)
		}
		else if curr == ")" {
			if stack.count == 0 {
				vps = false
				break
			}else {
				var prev = stack.popLast()!
				if prev == ")" {
					vps = false
					break
				}
			}
		}
	}
	
	if stack.count > 0 || !vps{
		print("NO")
	}else {
		print("YES")
	}
}
