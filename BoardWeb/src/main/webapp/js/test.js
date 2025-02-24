/**
 * js/test.js
 * 
 */
console.log('1');

let json = `{"name":"홍길동", "age":20}`;
let obj = JSON.parse(json);
document.querySelector('input[name="name"]').value = obj.name;
document.querySelector('input[name="age"]').value = obj.age;

// 서버(서블릿) - jsp페이지.
// Ajax = Asynchronous javascript and xml
fetch('testData.do')																																																																																																																																																																																																																																																																																																																																																																																														
	.then(function(result) {
		console.log(result); // body: stream
		return result.json();
	})
	.then(function(result) {
		console.log(result);
		document.querySelector('input[name="name"]').value = result.name;
		document.querySelector('input[name="age"]').value = result.age;
	console.log('2');
	})
	console.log('3');
