/**
 *  member.js
 */
// 삭제함수.
function deleteRow(id) {
	fetch("removeMember.do?mid="+id)
		.then(function(result) {
			return result.json() 
		})
		.then((result) =>{
			if(result.retCode == "OK"){
				document.querySelector('.btn').remove();
			}else if(result.retCode == "NG"){
				alert('삭제오류 발생');
			} else {
				alert('알수 없는 코드입니다.');
			}
		})
	
}// end of deleteRow
// 테이블 그려주기.
fetch("testData.do")
	.then(function(result) {
		return result.json(); // stream -> object
	})
	.then(function(result) {
		const memberAry = result;
		memberAry.forEach(function(member,idex) {
			const target = document.querySelector('#list');
			const html = `<tr class=${idex}>
		<td>${member.memberId}</td>
		<td>${member.passwd}</td>
		<td>${member.memberName}</td>
		<td>${member.responsibility}</td>
		<td><button onclick="deleteRow('${member.memberId}')" class="btn btn-danger">삭제</button></td>
		</tr>`;
			target.insertAdjacentHTML('beforeend', html);
		});
	})