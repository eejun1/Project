<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>

<title></title>

<jsp:include page="../top.jsp" />
<br />
<br />
<br />
<div class="container">
	<div class="row">
		<ul class="dropdown-menu dropup"
			style="display: inline-block; position: fixed; top: 90%; max-width: 50px; margin-left: 10px;">
			<li class="dropdown" style="text-align: center;">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					카테고리 필터
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu" role="menu">
					<li>
						<a href="#" class="categoryOuter" id="categoryOuter" style="text-align: center;">OUTER</a>
					</li>
					<li>
						<a href="#" class="categoryTop" id="categoryTop" style="text-align: center;">TOP</a>
					</li>
					<li>
						<a href="#" class="categoryBottom" id="categoryBottom" style="text-align: center;">BOTTOM</a>
					</li>
					<li>
						<a href="#" class="categoryDress" id="categoryDress" style="text-align: center;">DRESS</a>
					</li>
					<li>
						<a href="#" class="categoryEtc" id="categoryEtc" style="text-align: center;">ETC.</a>
					</li>
					<li class="divider"></li>
					<li>
						<a href="#" class="clearCategory" id="clearCategory" style="text-align: center;">필터 삭제</a>
					</li>
				</ul>
			</li>
		</ul>
		<div class="col-lg-12">
			<h1 class="page-header">
				사용자용 상품 페이지
				<br />
				선택 필터 [
				<span id="currentCategory">없음</span>
				]
			</h1>
		</div>
		<core:forEach items="${requestScope.item_list }" var="itemList" varStatus="index">
			<div class="col-lg-4 col-md-4 col-xs-6 thumb">
				<a class="thumbnail">
					<img class="img-responsive" src="${itemList.itemTitleSrc }" alt="">
				</a>
				<form>
					<input type="hidden" class="form-control" name="userRequest" value="DeleteItemServlet">
					<input type="hidden" class="itemSeq" name="itemSeq" value="${itemList.itemSeq }">
					<input type="hidden" class="itemName" name="itemName" value="${itemList.itemName }">
					<input type="hidden" class="itemCategory" name="itemCategory" value="${itemList.itemCategory }">
					<input type="hidden" class="itemQuantity" name="itemQuantity" value="${itemList.itemQuantity }">
					<input type="hidden" class="itemPrice" name="itemPrice" value="${itemList.itemPrice }">
					<input type="hidden" class="itemContent" name="itemContent" value="${itemList.itemContent }">
					<input type="hidden" class="itemTitleSrc" name="itemTitleSrc" value="${itemList.itemTitleSrc }">
					<core:set var="itemSrcList">${itemList.itemSrc }</core:set>
					<core:forTokens items="${itemSrcList }" delims="," var="value">
						<input type="hidden" class="itemSrc" name="itemSrc" value="${value }">
					</core:forTokens>
				</form>
			</div>
		</core:forEach>
	</div>
</div>

<div class="modal fade" id="imagemodal" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body">
				<div class="row">
					<div class="collapse-group">
						<div class="col-lg-6 col-md-6 col-xs-12">
							<img src="#" class="imagepreview details-collapse-btn" style="width: 100%;">
							<p class="collapse itemSrcCollapse"></p>

						</div>
						<div class="col-lg-6 col-md-6 col-xs-12">

							<form action="ItemFrontController" method="post">
								<input type="hidden" class="form-control" name="userRequest" value="AddToCartServlet">
								<input type="hidden" class="form-control" name="itemSeqInModal" id="itemSeqInModal" value="" />
								<div class="form-group">
									상품이름
									<input type="text" class="form-control" name="itemNameInModal" id="itemNameInModal" value="" readonly />
								</div>
								<div class="form-group">
									가격
									<input type="text" class="form-control" name="itemPriceInModal" id="itemPriceInModal" value="" readonly />
								</div>
								<div class="form-group">
									재고
									<input type="text" class="form-control" name="itemQuantityInModal" id="itemQuantityInModal" value="" readonly />
								</div>
								<div class="form-group">
									카테고리
									<input type="text" class="form-control" name="itemCategoryInModal" id="itemCategoryInModal" value="" readonly />
								</div>
								<div class="form-group">
									상세정보
									<textarea style="resize: none;" rows="5" class="form-control" name="itemContentInModal" id="itemContentInModal"
										readonly></textarea>
								</div>
								수량
								<input type="number" class="form-control" name="requestItemQuantityInModal" id="requestItemQuantityInModal"
									value="" />
								<button type="submit" class="btn btn-success">장바구니 담기</button>

								<button type="button" class="btn btn-danger" data-dismiss="modal">창 닫기</button>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	/* 상품 이미지 클릭시 정보 */
 $(function() {
	 $('.thumb').on(
	   'click',
	   function() {

		   var category;

		   $('.imagepreview').attr('src', $(this).find('img').attr('src'));
		   /* 모달 itemSrc 이미지 출력 부분 */
		   var str1 = "";
		   $('.itemSrcCollapse').empty();
		   $(this).find('input[name=itemSrc]').each(
		     function(index, item) {
			     var x = $(item).attr("value");
			     str1 += "<img class='img-responsive' id='itemSrc' src='" + x
			       + "'style='width: 100%;'>";
		     });
		   $('.itemSrcCollapse').append(str1);
		   /*  */
		   document.getElementById("itemSeqInModal").value = $(this).find(".itemSeq")
		     .attr("value");
		   document.getElementById("itemNameInModal").value = $(this).find(
		     ".itemName").attr("value");
		   document.getElementById("itemPriceInModal").value = $(this).find(
		     ".itemPrice").attr("value");

		   switch ($(this).find(".itemCategory").attr("value")) {
		   case '0':
			   category = "OUTER";
			   break;
		   case '1':
			   category = "TOP";
			   break;
		   case '2':
			   category = "BOTTOM";
			   break;
		   case '3':
			   category = "DRESS";
			   break;
		   case '4':
			   category = "ETC.";
			   break;
		   }
		   document.getElementById("itemCategoryInModal").value = category;
		   document.getElementById("itemContentInModal").innerHTML = $(this).find(
		     ".itemContent").attr("value");
		   document.getElementById("itemQuantityInModal").value = $(this).find(
		     ".itemQuantity").attr("value");
		   $('#imagemodal').modal('show');
	   });
 });

 /*  카테고리 버튼 필터링 */
 $(function() {
	 $('.categoryOuter').click(
	   function() {
		   $('input[name=itemCategory]').parents("div").addClass('hidden');
		   $('.itemCategory[value=0]').parents("div").removeClass('hidden');
		   document.getElementById('currentCategory').innerHTML = document
		     .getElementById('categoryOuter').innerHTML;
	   });

	 $('.categoryTop').click(
	   function() {
		   $('input[name=itemCategory]').parents("div").addClass('hidden');
		   $('.itemCategory[value=1]').parents("div").removeClass('hidden');
		   document.getElementById('currentCategory').innerHTML = document
		     .getElementById('categoryTop').innerHTML;
	   });

	 $('.categoryBottom').click(
	   function() {
		   $('input[name=itemCategory]').parents("div").addClass('hidden');
		   $('.itemCategory[value=2]').parents("div").removeClass('hidden');
		   document.getElementById('currentCategory').innerHTML = document
		     .getElementById('categoryBottom').innerHTML;
	   });

	 $('.categoryDress').click(
	   function() {
		   $('input[name=itemCategory]').parents("div").addClass('hidden');
		   $('.itemCategory[value=3]').parents("div").removeClass('hidden');
		   document.getElementById('currentCategory').innerHTML = document
		     .getElementById('categoryDress').innerHTML;
	   });

	 $('.categoryEtc').click(
	   function() {
		   $('input[name=itemCategory]').parents("div").addClass('hidden');
		   $('.itemCategory[value=4]').parents("div").removeClass('hidden');
		   document.getElementById('currentCategory').innerHTML = document
		     .getElementById('categoryEtc').innerHTML;
	   });
	 $('.clearCategory').click(function() {
		 $('input[name=itemCategory]').parents("div").addClass('hidden');
		 $('.itemCategory').parents("div").removeClass('hidden');
		 document.getElementById('currentCategory').innerHTML = '없음';
	 });
 });

 /* collapse 토글 */
 $('.details-collapse-btn').on('click', function(e) {
	 e.preventDefault();
	 var $this = $(this);
	 var $collapse = $this.closest('.collapse-group').find('.collapse');
	 $collapse.collapse('toggle');
 });
</script>
</body>
</html>