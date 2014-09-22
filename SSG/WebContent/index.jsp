<jsp:include page="/includes/header.jsp" />

<section>

<h1>SSG Active Directory - Retrieving Disabled Fields</h1>

<form action="/SSGController/ADAccountDisabled" method="post">
    <label>Enter Date</label>
    <input type="text" name="date" value="mm/dd/yyyy"><br/>
    <input type="submit" value="Show">
</form>

</section>

<jsp:include page="/includes/footer.jsp" />