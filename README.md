


https://www.yoursite.com/send

	post json
		{
			"size":"5",
			"money":"10",
			"sender":"he",
			"wishes":"Merry Christmas!圣诞快乐",
			"imgUrl":"/hi.jpg"
		}
	
	return 
		{
		  "errcode": 200/500
		  "result": "OK",
		  "errmsg": "OK"
		}

		
https://www.yoursite.com/receive/get

	post json
		{
			"id":"57509cf2-2d34-4220-a168-9cf0c1c515bf",
			"nickname":"user5",
			"imgUrl":"./test/haha.jpg",
			"grabMoney":null,"picUrl":null,
			"beauty":null,
			"happy":null
		}
	
	return
		{
		  "errcode": 200/500
		  "result": "OK",
		  "errmsg": "OK"
		}

https://www.yoursite.com/receive/open

注意：这个post的时候有可能返回500 
不是errcode:500 是真正的http status 500 
意味着我抛出了异常 也就是图片不是人像识别不了 这里要用fail来处理 可以弹出个提示框让他换图片


	post json
		{
			"id":"57509cf2-2d34-4220-a168-9cf0c1c515bf",
			"nickname":"user5",
			"imgUrl":"./test/haha.jpg",
			"grabMoney":null,
			"picUrl":"picture-1255478844.cosgz.myqcloud.com/666.jpg",
			"beauty":null,
			"happy":null
		}
	
	return 
		{
		  "errcode": 200/500
		  "result": {
			"happy_rank": "害羞啥呢？",
			"code": 0,
			"beauty": 81,
			"money": 3.33,
			"beauty_rank": "三百六十度无死角的仰慕，简直帅绝人寰",
			"sexAndAge_rank": "正处青春年华，如春花初放，似莲之清纯。",
			"happy": 11,
			"sex": "female",
			"weight": 0.76,
			"wmoney": 2.53,
			"age": 14
		  },
		  "errmsg": "OK"
		}
		
https://www.yoursite.com/packList

	get 无参数
	
	return
		{
		  "errcode": 200,
		  "result": [{
			"packID": "57509cf2-2d34-4220-a168-9cf0c1c515bf",
			"sender": "he",
			"list": [{
			  "id": null,
			  "nickname": "user5",
			  "imgUrl": "./test/haha.jpg",
			  "grabMoney": 2.53,
			  "picUrl": null
			}],
			"size": 5,
			"wishes": "Merry Christmas!圣诞快乐",
			"sendTime": "2017-12-11 22:15:03",
			"imgUrl": "/hi.jpg",
			"grabbedSize": 1
		  }, {
			"packID": "f1cb30eb-6604-4740-9491-7ece8fc01885",
			"sender": "he",
			"list": [],
			"size": 5,
			"wishes": "Merry Christmas!圣诞快乐",
			"sendTime": "2017-12-11 22:10:23",
			"imgUrl": "/hi.jpg",
			"grabbedSize": 0
		  }],
		  "errmsg": "OK"
		}
		
		
		
https://www.yoursite.com/list?id=57509cf2-2d34-4220-a168-9cf0c1c515bf

	get 传参id
	
	return 
	
		{
		  "errcode": 200,
		  "size": 5,
		  "sender": "he",
		  "grabbedSize": 1,
		  "money":5,
		  "errmsg": "OK",
		  "grabsList": [{
			"id": null,
			"nickname": "user5",
			"imgUrl": "./test/haha.jpg",
			"grabMoney": 2.53,
			"picUrl": null
		  }],
		  "wishes": "Merry Christmas!圣诞快乐",
		  "sendTime": "2017-12-11 22:15:03"
		}
	