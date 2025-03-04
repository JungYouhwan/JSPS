/**
 * api.js
 */
let centerAll = [];

// 이벤트(select) 등록.
document.getElementById('centerList').addEventListener('change', function(e){
	let sidoName = e.target.value; // "서울특별시", "인천광역시" .....
	let filterSido = [];
	filterSido = centerAll.filter(item => {
		if(item.sido == sidoName){
			return true;
		}
		return false;
	})
	console.log(filterSido);
	makeCenterList(filterSido);
}); // change이벤트.
function makeCenterList(centerAry = []) {
	let fields = ['id', 'centerName', 'phoneNumber', 'sido'];
	// 새로 생성시 기존목록 삭제.
	document.getElementById('list').innerHTML = '';
	// 센터정보.
	centerAry.forEach(center =>{
		// tr>td*4
		let tr = document.createElement('tr');
		tr.addEventListener('click', function() {
			window.open('map.do?lat=' + center.lat + '&lng=' + center.lng +'&name=' + center.facilityName)
		});
		for(let i=0; i<fields.length; i++){
			let td = document.createElement('td');		
			td.innerHTML = center[fields[i]]
			tr.appendChild(td); 
		}
		document.getElementById('list').appendChild(tr);
	});
}
// Ajax.
fetch("https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&serviceKey=1Va0oUzQWWlT0mjQXD1gjQLn08AKcyQF4yeMjxX6u51K5PsmYlq0POQGQbrGHpGg4OwYuojiPWeQ%2FCsTBJk23w%3D%3D")
.then(result => result.json())
.then(result => {
	centerAll = result.data;
	makeSidoList();	
})
.catch(err => console.log(err));
// 시도정보 중복제거 후 화면 출력.
function makeSidoList() {
	let sidoList = []; // ['서울특별시, ]'']
	for(let i=0; i<centerAll.length; i++) {
		if(sidoList.indexOf(centerAll[i].sido) == -1){
		sidoList.push(centerAll[i].sido);
		}
	}
	sidoList.forEach(sido => {
		let opt = document.createElement('option');
		opt.innerHTML = sido;
		document.getElementById('centerList').appendChild(opt);
	})	
}