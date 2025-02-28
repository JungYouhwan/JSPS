/**
 * reply_dt.js
 */
let delNo = 0;
let table = new DataTable('#example', {
	ajax: 'datatable.do?bno=' + bno,
	lengthMenu: [
		[5, 10, 25, 50, -1],
		[5, 10, 25, 50, 'All']
	]
});
// 화면에서 row 추가. 등록
let counter = 'hello';
let param = {원본글(bno), replyer(logid), reply(#reply)}
function addNewRow() {
	// 원본글(bno), replyer(logid), reply(#reply)
    table.row
        .add([
           rvo.replyNo,
		   rvo.reply,
		   rvo.replyer,
		   rvo.replyDate
        ])
        .draw(false);
 
    counter++;
};

document.querySelector('#addReply').addEventListener('click', addNewRow);

// tr 선택/ 선택해제.
table.on('click', 'tbody tr', (e) => {
	let classList = e.currentTarget.classList; // ['select']
	if (classList.contains('selected')) {
		classList.remove('selected');
	}
	else {
		table.rows('.selected').nodes().each((row) => row.classList.remove('selected'));
		classList.add('selected');
		delNo = e.currentTarget.children[0].innerText; // 댓글 번호.
	}
});

document.querySelector('#button').addEventListener('click', function() {
	svc.removeReply(delNo,
		function(result) {
			if (result.retCode == 'OK') {
				table.row('.selected').remove().draw(false);
			} else {
				alert('처리중 오류');
			}
		},
		function(err) {
			console.log(err);
		})
	// 화면삭제.
	table.row('.selected').remove().draw(false);
});

