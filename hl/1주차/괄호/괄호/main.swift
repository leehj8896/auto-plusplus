//
//  main.swift
//  괄호
//
//  Created by HL on 2022/08/15.
//

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

/*
6
(())())
(((()())()
(()())((()))
((()()(()))(((())))()
()()()()(()()())()
(()((())()(

3
((
))
())(()

*/
