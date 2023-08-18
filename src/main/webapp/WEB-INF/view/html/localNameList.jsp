<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>localNameList.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
<script>
	$(document).ready(function(){
		
		const x = [];
		const y = [];
		
		// 동기호출로 x,y 값을 셋팅
		$.ajax({
			async : false, // true : 비동기(기본값), false : 동기
			url : '/rest/localNameList',
			type : 'get',
			success : function(model){
				// backend Model -> Frontend Model 변경
				/* 
					model -> ('model' : [{localName:'부산',cnt:10},
									{localName:'서울',cnt:22}])
				*/
				console.log(model);
				model.forEach(function(item, index){ 
					$('#target').append('<tr>');
					$('#target').append('<td>'+item.localName+'</td>');
					$('#target').append('<td>'+item.cnt+'</td>');
					$('#target').append('</tr>');
					
					// chart 모델 생성
					x.push(item.localName);
					y.push(item.cnt);
				});
			}
		});
		
		/* 
			let html = `<tr>
							<td>GDJ66</td>
							<td>25</td>
						</tr>`;  //AJax API 결과물(자료구조) 출력
			$('#target').html(html); 
		*/
		
		
		//const barColors = ["red", "green","blue","orange","brown"];
		new Chart("target2", {
		  type: "bar",
		  data: {
		    labels: x,
		    datasets: [{
		     // backgroundColor: barColors,
		      data: y
		    }]
		  },
		 // options: {...}
		});
	});
</script>
</head>
<body>
	<h1>AJax API 사용으로 localNameList 가져오기</h1>
	
	<table border="1">
		<thead>
			<tr>
				<td>지역이름</td>
				<td>게시글수</td>
			</tr>
		</thead>
		<tbody id="target">
		</tbody>
	</table>
	<canvas id="target2" style="width:100%;max-width:700px"></canvas>
</body>
</html>