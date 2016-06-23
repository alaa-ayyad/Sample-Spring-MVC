<#import "/spring.ftl" as spring/>
 
<@customize.page title="Form Test">

<form id="my-form" name="form" class="form" role="form" action='<@spring.url "/submit"/>' method="POST">

	<div><label class="telus-input-label" for="name">Name</label></div>
	<@spring.formInput "form.name" , "id='field-name' " />
	<br>
	<div><label class="telus-input-label" for="description">Message</label></div>
	<@spring.formTextarea "form.description" , "id='field-description' " />

	<br>
	<button id="submit-btn" type="submit" class="" >Submit</button>

</form>
  
</@customize.page>
