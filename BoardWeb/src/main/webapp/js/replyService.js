/**
 * replyService.js 
 */

const svc = {
	name: "Hong",
	showName: function() {
		return this.name;
	},
	// 목록메소드.
	replyList: function(bno, successCallback, errorCallback) {
		fetch('replyList.do?bno=' + bno)
			.then(result => result.json())
			.then(successCallback) // 정상처리시 실행함수.
			.catch(errorCallback) // 에러시 실행할 함수.
	},
	// 등록메소드.
	addReply(param = { bno, reply, replyer }, successCallback, errorCallback) {
		fetch('addReply.do?bno=' + param.bno + '&reply=' + param.reply + '&replyer=' + param.replyer)
			.then(result => result.json())
			.then(successCallback) // 정상처리시 실행함수.
			.catch(errorCallback) // 에러시 실행할 함수.

	},
	// 삭제메소드.
	removeReply(rno=1, successCallback, errorCallback) {
		fetch('removeReply.do?rno=' + rno)
		.then(result => result.json())
		.then(successCallback)
		.catch(errorCallback)
	}
}