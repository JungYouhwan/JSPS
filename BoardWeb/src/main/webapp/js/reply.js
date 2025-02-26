/**
 * reply.js 
 */
console.log(svc.showName());
let page = 1; // 페이징값.
// 댓글
function makeReply(reply = {}) {
	let html = `<li data-id="${reply.replyNo}" class="list-group-item list-group-item-light">
					<span class="col-sm-2">${reply.replyNo}</span>
					<span class="col-sm-5">${reply.reply}</span>
					<span class="col-sm-2">${reply.replyer}</span>
					<span class="col-sm-2"><button class="btn btn-danger" onclick="deleteRow('${reply.replyNo}')">삭제</button></span>
				</li>`;
	return html;
};
// 삭제메소드
// confirm = alert창이 뜨며 수락누르면 true 취소 누르면 false
function deleteRow(rno) { //
	if (!confirm("삭제하겠습니까?")) {
		alert('취소합니다.');
		return;
	}
	svc.removeReply(rno //
		, function(result) { //
			if (result.retCode == 'OK') {
				document.querySelector('li[data-id="' + rno + '"]').remove();
				
				showPageList();
				showPageingList();
			}
		}
		, function(err) { console.log(err); })
};
// 목록리스트
function showPageList() {
	svc.replyList({ bno, page } // 원본글번호
		,// 성공함수
		function(result) {
			document.querySelectorAll('li[data-id]').forEach(function(elem) {
				elem.remove();
			});
			let resultAry = result;
			resultAry.forEach(function(reply) {
				let target = document.querySelector('.reply>.content>ul');
				target.insertAdjacentHTML('beforeend', makeReply(reply));
			});
		},
		function(err) {
			console.log(err);
		})
};
// 목록.
showPageList();
// 페이징 생성.
function showPageingList() {
	svc.makePaging(bno,
		function(result) {
			const totalCnt = result.totalCnt;
			// startPage, endPage, currentPage
			// prev, next 계산. 11.. 15 .. 20
			let currPage = page;
			let endPage = Math.ceil(currPage / 10) * 10;
			let startPage = endPage - 9;
			let realEnd = Math.ceil(totalCnt / 5);
			endPage = endPage > realEnd ? realEnd : endPage;
			let prev = startPage != 1 ? true : false;
			let next = endPage != realEnd ? true : false;

			let target = document.querySelector('div.footer>nav>ul');
			target.innerHTML = '';
			let html = '';
			// 링크 생성.
			if (prev) {
				html = `<li class="page-item">
				<a class="page-link" data-page="${startPage - 1}">Previous</a>
				</li>`;
			} else {
				html = `<li class="page-item disabled">
				<a class="page-link">Previous</a>
				</li>`
			}
			target.insertAdjacentHTML('beforeend', html);
			for (let p = startPage; p < endPage; p++) {
				if (p == currPage) {
					html = `<li class="page-item active"><a class="page-link" href="#" data-page="${p}">${p}</a></li>`;
				} else {
					html = `<li class="page-item"><a class="page-link" href="#" data-page="${p}">${p}</a></li>`;
				}
				target.insertAdjacentHTML('beforeend', html);
			}
			// 이후 페이지 존재.
			if (next) {
				html = `<li class="page-item">
	        	       <a class="page-link" href="#" data-page="${endPage + 1}">Next</a></li>`;
			} else {
				html = `<li class="page-item disabled">
					   <a class="page-link" href="#">Next</a></li>`
			}
			target.insertAdjacentHTML('beforeend', html);
			addLinkEvent();
		},
		function(err) {
			console.log(err);
		}
	);
}
// 페이징 생성.

// 댓글등록 이벤트. id="addReply"
document.querySelector('#addReply').addEventListener('click', function() {
	// 글번호 : bno, 작성자: logid, 댓글내용: ?
	const reply = document.querySelector('#reply').value;
	const replyer = logid;
	if (!reply || !replyer) {
		alert('필수입력값을 확인.');
		return;
	}
	const parm = { bno, reply, replyer }
	svc.addReply(parm,//
		function(result) {
			if (result.retCode == 'OK') {
				/*const html = makeReply(result.retVal);
				let target = document.querySelector('.reply>.content>ul');
				target.insertAdjacentHTML('beforeend', html);*/
				page=1;
				showPageList();
				showPageingList();
				
			} else {
				alert('처리 예외 발생.');
			}
		}
		, function(err) { console.log(err) });
})

// 페이징목록의 링크() 이벤트. [a,a,a,a,a,a....]
// nav의 바로 하위 그래서 공백처리
function addLinkEvent() {
	document.querySelectorAll('div.footer>nav a').forEach(function(item) {
		item.addEventListener('click', function(e) {
			e.preventDefault(); // 페이징처리의 버튼클릭의 기본기능이 X
			page = e.target.getAttribute('data-page'); // 링크를 클릭하면 페이지정보.
			// 목록 보기
			showPageList();
			// 페이지 생성.
			showPageingList();
		});
	})
};
showPageingList();