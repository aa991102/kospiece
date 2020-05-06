<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<div class="main-map">
	<div class="map">
	맵
	</div>
	<div class="map-detail">
		<form name="company-inform" method ="post" class="company-inform">
		    <input type="text" name="company" class="company-name" />
		    <input type="submit" value="검색"/>
		</form>
		<table border="1" width="800" align="center" class="company-detail">
			<tr>
				<th colspan="12">회사명 회사코드
				<img src="img/star.png"/>
				<a href="#">투자하기</a>
				</th>
			</tr>
			<tr>
				<th colspan="2" width="16.6%">전일비</th>
				<td colspan="2" width="16.6%"></td>
				<th colspan="2" width="16.6%">전일비</th>
				<td colspan="2" width="16.6%"></td>
				<th colspan="2" width="16.6%">등락률</th>
				<td colspan="2"></td>
			</tr>
			<tr>
				<th colspan="2">거래량</th>
				<td colspan="2"></td>
				<th colspan="2">거래대금</th>
				<td colspan="2"></td>
				<th colspan="2">52주고가</th>
				<td colspan="2"></td>
			</tr>
			<tr>
				<th colspan="2">시가총액</th>
				<td colspan="2"></td>
				<th colspan="2">업종</th>
				<td colspan="2"></td>
				<th colspan="2">세부업종</th>
				<td colspan="2"></td>
			</tr>
			<tr>
				<th colspan="12">동일 세부업종 현황</th>
			</tr>
			<tr>
				<th colspan="3" width="200">종목</th>
				<th colspan="3" width="200">현재가</th>
				<th colspan="3" width="200">전일비</th>
				<th colspan="3" width="200">등락률</th>
			</tr>
			<tr>
				<td colspan="3">00</td>
				<td colspan="3">00</td>
				<td colspan="3">00</td>
				<td colspan="3">00</td>
			</tr>
		</table>
	</div>
</div>